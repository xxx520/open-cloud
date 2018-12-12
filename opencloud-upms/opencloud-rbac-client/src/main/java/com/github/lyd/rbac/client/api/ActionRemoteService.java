package com.github.lyd.rbac.client.api;

import com.github.lyd.common.model.PageList;
import com.github.lyd.common.model.ResultBody;
import com.github.lyd.rbac.client.entity.ResourceAction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ActionRemoteService {
    /**
     * 获取动作资源列表
     *
     * @return
     */
    @PostMapping("/actions")
    ResultBody<PageList<ResourceAction>> actions(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "keyword", required = false) String keyword
    );


    /**
     * 获取动作资源
     *
     * @param actionId actionId
     * @return 应用信息
     */
    @GetMapping("/actions/{actionId}")
    ResultBody<ResourceAction> getAction(@PathVariable("actionId") Long actionId);

    /**
     * 添加动作资源
     *
     * @param actionCode  动作编码
     * @param actionName  动作名称
     * @param menuId      归属菜单
     * @param url         请求路径
     * @param enabled     是否启用
     * @param priority    优先级越小越靠前
     * @param description 描述
     * @return
     */
    @PostMapping("/actions/add")
    ResultBody<Boolean> addAction(
            @RequestParam(value = "actionCode") String actionCode,
            @RequestParam(value = "actionName") String actionName,
            @RequestParam(value = "menuId") Long menuId,
            @RequestParam(value = "url", required = false, defaultValue = "") String url,
            @RequestParam(value = "enabled", defaultValue = "true") Boolean enabled,
            @RequestParam(value = "priority", required = false, defaultValue = "0") Integer priority,
            @RequestParam(value = "description", required = false, defaultValue = "") String description
    );

    /**
     * 编辑动作资源
     *
     * @param actionId    动作ID
     * @param actionCode  动作编码
     * @param actionName  动作名称
     * @param menuId      归属菜单
     * @param url         请求路径
     * @param enabled     是否启用
     * @param priority    优先级越小越靠前
     * @param description 描述
     * @return
     */
    @PostMapping("/actions/update")
    ResultBody<Boolean> updateAction(
            @RequestParam("actionId") Long actionId,
            @RequestParam(value = "actionCode") String actionCode,
            @RequestParam(value = "actionName") String actionName,
            @RequestParam(value = "menuId") Long menuId,
            @RequestParam(value = "url", required = false, defaultValue = "") String url,
            @RequestParam(value = "enabled", defaultValue = "true") Boolean enabled,
            @RequestParam(value = "priority", required = false, defaultValue = "0") Integer priority,
            @RequestParam(value = "description", required = false, defaultValue = "") String description
    );

    /**
     * 禁用动作资源
     *
     * @param actionId 动作ID
     * @return
     */
    @PostMapping("/actions/disable")
    ResultBody<Boolean> disableAction(
            @RequestParam("actionId") Long actionId
    );

    /**
     * 启用动作资源
     *
     * @param actionId 动作ID
     * @return
     */
    @PostMapping("/actions/enable")
    ResultBody<Boolean> enableAction(
            @RequestParam("actionId") Long actionId
    );

    /**
     * 移除动作
     *
     * @param actionId 动作ID
     * @return
     */
    @PostMapping("/actions/remove")
    ResultBody<Boolean> removeAction(
            @RequestParam("actionId") Long actionId
    );
}
