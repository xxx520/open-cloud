package com.github.lyd.rbac.producer.controller;

import com.github.lyd.common.model.PageList;
import com.github.lyd.common.model.PageParams;
import com.github.lyd.common.model.ResultBody;
import com.github.lyd.rbac.client.api.AppInfoRemoteService;
import com.github.lyd.rbac.client.dto.AppInfoDto;
import com.github.lyd.rbac.producer.service.AppInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息
 *
 * @author liuyadu
 */
@Api(tags = "应用管理")
@RestController
public class AppInfoController implements AppInfoRemoteService {
    @Autowired
    private AppInfoService appInfoService;


    /**
     * 获取应用列表
     *
     * @return
     */
    @ApiOperation(value = "应用列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form"),
            @ApiImplicitParam(name = "limit", value = "显示条数:最大999", paramType = "form"),
            @ApiImplicitParam(name = "keyword", value = "查询字段", paramType = "form"),
    })
    @PostMapping("/apps")
    @Override
    public ResultBody<PageList<AppInfoDto>> apps(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        PageList<AppInfoDto> pageList = appInfoService.findListPage(new PageParams(page, limit), keyword);
        return ResultBody.success(pageList);
    }

    /**
     * 获取应用信息
     *
     * @param appId appId
     * @return 应用信息
     */
    @ApiOperation(value = "获取应用信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID", defaultValue = "1", required = true, paramType = "path"),
    })
    @GetMapping("/apps/{appId}")
    @Override
    public ResultBody<AppInfoDto> getApp(
            @PathVariable("appId") String appId
    ) {
        AppInfoDto appInfo = appInfoService.getAppInfo(appId);
        return ResultBody.success(appInfo);
    }

    /**
     * 添加应用
     *
     * @param appName      应用名称
     * @param appNameEn    应用英文名称
     * @param appType      应用类型:server-应用服务 app-手机应用 pc-PC网页应用 wap-手机网页应用
     * @param appIcon      应用图标
     * @param description  应用说明
     * @param os           手机应用操作系统:ios-苹果 android-安卓
     * @param redirectUrls 重定向地址
     * @param scopes       授权范围
     * @param resourceIds  资源服务器ID
     * @param authorities  应用权限,多个用逗号隔开
     * @return
     */
    @ApiOperation(value = "添加应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appName", value = "应用名称", defaultValue = "", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appNameEn", value = "应用英文名称", defaultValue = "", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appType", value = "应用类型(server-应用服务 app-手机应用 pc-PC网页应用 wap-手机网页应用)", allowableValues = "server,app,pc,wap", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appIcon", value = "应用图标", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "description", value = "应用说明", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "os", value = "手机应用操作系统", allowableValues = "android,ios", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "redirectUrls", value = "重定向地址(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "scopes", value = "授权范围(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "resourceIds", value = "资源服务器ID(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "authorities", value = "应用权限(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
    })
    @PostMapping("/apps/add")
    @Override
    public ResultBody<Boolean> addApp(
            @RequestParam(value = "appName") String appName,
            @RequestParam(value = "appNameEn") String appNameEn,
            @RequestParam(value = "appType") String appType,
            @RequestParam(value = "appIcon", required = false) String appIcon,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "os", required = false) String os,
            @RequestParam(value = "redirectUrls", required = false) String redirectUrls,
            @RequestParam(value = "scopes", required = false) String scopes,
            @RequestParam(value = "resourceIds", required = false) String resourceIds,
            @RequestParam(value = "authorities", required = false) String authorities
    ) {
        Boolean result = appInfoService.addAppInfo(appName, appNameEn, appType, appIcon, description, os, redirectUrls, scopes, resourceIds, authorities);
        return ResultBody.success(result);
    }

    /**
     * 编辑应用
     *
     * @param appId        应用Id
     * @param appName      应用名称
     * @param appNameEn    应用英文名称
     * @param appType      应用类型:server-应用服务 app-手机应用 pc-PC网页应用 wap-手机网页应用
     * @param appIcon      应用图标
     * @param description  应用说明
     * @param os           手机应用操作系统:ios-苹果 android-安卓
     * @param redirectUrls 重定向地址
     * @param scopes       授权范围
     * @param resourceIds  资源服务器ID
     * @param authorities  应用权限,多个用逗号隔开
     * @return
     */
    @ApiOperation(value = "编辑应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用Id", defaultValue = "", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appName", value = "应用名称", defaultValue = "", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appNameEn", value = "应用英文名称", defaultValue = "", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appType", value = "应用类型(server-应用服务 app-手机应用 pc-PC网页应用 wap-手机网页应用)", allowableValues = "server,app,pc,wap", required = true, paramType = "form"),
            @ApiImplicitParam(name = "appIcon", value = "应用图标", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "description", value = "应用说明", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "os", value = "手机应用操作系统", allowableValues = "android,ios", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "redirectUrls", value = "重定向地址(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "scopes", value = "授权范围(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "resourceIds", value = "资源服务器ID(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "authorities", value = "应用权限(多个用“,”隔开)", defaultValue = "", required = false, paramType = "form"),
    })
    @PostMapping("/apps/update")
    @Override
    public ResultBody<Boolean> updateApp(
            @RequestParam("appId") String appId,
            @RequestParam(value = "appName") String appName,
            @RequestParam(value = "appNameEn") String appNameEn,
            @RequestParam(value = "appType") String appType,
            @RequestParam(value = "appIcon", required = false) String appIcon,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "os", required = false) String os,
            @RequestParam(value = "redirectUrls", required = false) String redirectUrls,
            @RequestParam(value = "scopes", required = false) String scopes,
            @RequestParam(value = "resourceIds", required = false) String resourceIds,
            @RequestParam(value = "authorities", required = false) String authorities
    ) {
        Boolean result = appInfoService.updateInfo(appId, appName, appNameEn, appType, appIcon, description, os, redirectUrls, scopes, resourceIds, authorities);
        return ResultBody.success(result);
    }

    /**
     * 重置秘钥
     *
     * @param appId 应用Id
     * @return 应用信息
     */
    @ApiOperation(value = "重置秘钥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用Id", defaultValue = "", required = true, paramType = "form"),
    })
    @PostMapping("/apps/reset")
    @Override
    public ResultBody<Boolean> resetSecret(
            @RequestParam("appId") String appId
    ) {
        Boolean result = appInfoService.restSecret(appId);
        return ResultBody.success(result);
    }

    /**
     * 删除应用
     *
     * @param appId 应用Id
     * @return 应用信息
     */
    @ApiOperation(value = "删除应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用Id", defaultValue = "", required = true, paramType = "form"),
    })
    @PostMapping("/apps/remove")
    @Override
    public ResultBody<Boolean> removeApp(
            @RequestParam("appId") String appId
    ) {
        Boolean result = appInfoService.removeApp(appId);
        return ResultBody.success(result);
    }
}
