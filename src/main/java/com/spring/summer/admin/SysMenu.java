package com.spring.summer.admin;

import com.spring.summer.admin.common.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author CXB
 * @ClassName SysMenu
 * @date 2022/8/20 9:19
 * @Description TODO
 */
@Data
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private String isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String status;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<SysMenu> children = new ArrayList<SysMenu>();

    @Override
    public String toString() {
        return "SysMenu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", query='" + query + '\'' +
                ", isFrame='" + isFrame + '\'' +
                ", isCache='" + isCache + '\'' +
                ", menuType='" + menuType + '\'' +
                ", visible='" + visible + '\'' +
                ", status='" + status + '\'' +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}
