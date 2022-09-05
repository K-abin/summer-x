package com.spring.summer.service.impl;

import com.spring.summer.admin.MetaVo;
import com.spring.summer.admin.RouterVo;
import com.spring.summer.admin.SysMenu;
import com.spring.summer.common.utils.Constants;
import com.spring.summer.common.utils.ForeachLog;
import com.spring.summer.common.utils.SecurityUtils;
import com.spring.summer.common.utils.UserConstants;
import com.spring.summer.mapper.SysMenuMapper;
import com.spring.summer.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;

/**
 * @Author CXB
 * @ClassName SysMenuServiceImpl
 * @date 2022/9/2 21:01
 * @Description TODO
 */

@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public Set<String> selectMenuPermissionByUserId(Long userId) {
        List<String> sysMenus = sysMenuMapper.selectMenuPermissionByUserId(userId);
        Set<String> menuPer = new HashSet<>();
        for (String menus : sysMenus) {
            if (menus.equals(null)) {
                menuPer.addAll(Arrays.asList(menus.trim().split(",")));
            }
        }
        ForeachLog.mateSet(menuPer,"用户的原始菜单");
        return menuPer;
    }


    /**
     * 构建前端路由所需要的菜单
     * @param menu
     * @return
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menu) {
        LinkedList<RouterVo> routerLists = new LinkedList<>();
        for (SysMenu mates : menu) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(mates.getVisible()));
            router.setName(getRouteName(mates));
            router.setPath(getRouterPath(mates));
            router.setComponent(getComponent(mates));
            router.setQuery(mates.getQuery());
            router.setMeta(new MetaVo(mates.getMenuName(), mates.getIcon(), StringUtils.equals("1", mates.getIsCache()), mates.getPath()));
            List<SysMenu> cMenus = mates.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(mates.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(mates)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(mates.getPath());
                children.setComponent(mates.getComponent());
                children.setName(StringUtils.capitalize(mates.getPath()));
                children.setMeta(new MetaVo(mates.getMenuName(), mates.getIcon(), StringUtils.equals("1", mates.getIsCache()), mates.getPath()));
                children.setQuery(mates.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (mates.getParentId().intValue() == 0 && isInnerLink(mates)) {
                router.setMeta(new MetaVo(mates.getMenuName(), mates.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(mates.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(mates.getMenuName(), mates.getIcon(), mates.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routerLists.add(router);
        }

        return routerLists;
    }

    /**
     * 获取组件
     * @param menu
     * @return
     *
     *
     */

    public String getComponent(SysMenu menu){
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)){
            component = menu.getComponent();
        }else if(StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() !=0 && isInnerLink(menu)){
            component = UserConstants.INNER_LINK;
        }else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)){
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }


    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenu menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    public String getRouterPath(SysMenu menu){
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() !=0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenu menu){
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && com.spring.summer.common.utils.StringUtils.ishttp(menu.getPath());
    }
    /**
     * 获取路由名称
     * @param menu
     * @return
     */
    public String getRouteName(SysMenu menu){
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为菜单）
        if (isMenuFrame(menu)){
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否时菜单内部跳转
     * @param menu
     * @return
     * getParentId = 0 一级菜单
     * getMenuType = C 菜单类型（菜单）
     * getIsFrame =1 是否菜单外链（否）
     * 是1一级菜单，菜单类型，不是菜单外链
     */
    private boolean isMenuFrame(SysMenu menu) {
    return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType()) &&
        menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }


    /**
     *根据用户ID查询菜单树
     * @param userId 用户ID
     * @return
     * menu 当时管理员时查询所有菜单，不是管理员时查询对应的菜单
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus =null;
        if (SecurityUtils.isAdmin(userId)){
            menus = sysMenuMapper.selectMenuTreeAll();
        }else {
            menus = sysMenuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus,0);
    }

    /**
     * 根据父节点ID获取所以子节点
     * @param list 传入的menu 菜单树
     * @param parentId
     * @return
     * iterator 迭代器，iterator.next 集合中的下一个元素，iterator.hasnext();
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list,int parentId){
        ArrayList<SysMenu> returnList = new ArrayList<>();
        for (Iterator<SysMenu> iterator=list.iterator(); iterator.hasNext();) {
            SysMenu t = (SysMenu) iterator.next();
            if (t.getParentId() == parentId){
                recursionFn(returnList,t);
            }
        }
        return returnList;
    }

    /**
     * 递归菜单元素
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list,SysMenu t){
        List<SysMenu> childList = getChildList(list,t);
        t.setChildren(childList);
        for (SysMenu tChild: childList
             ) {
            if (hasChild(list,tChild)){
                recursionFn(list,tChild);
            }
        }
    }

    /**
     *
     * @param list
     * @param t
     * @return
     */
    private List<SysMenu> getChildList(List<SysMenu> list,SysMenu t){
        List<SysMenu> tList = new ArrayList<>();
        Iterator<SysMenu> iterator = list.iterator();
        while (iterator.hasNext()){
            SysMenu next = (SysMenu) iterator.next();
            if (next.getParentId().longValue() == t.getMenuId().longValue()){
                tList.add(next);
            }
        }
        ForeachLog.mateList(list,"list原始数据");
        ForeachLog.mateList(tList,"list处理数据");
        return tList;
    }

    private boolean hasChild(List<SysMenu> list,SysMenu t){
        return getChildList(list,t).size() >0;
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return
     */
    public String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{Constants.HTTP, Constants.HTTPS},
                new String[]{"", ""});
    }
}
