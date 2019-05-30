package com.youran.generate.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:【代码生成】api</p>
 * <p>Description:</p>
 * @author: cbb
 * @date: 2017/5/13
 */
@Api(tags = "MetaCodeGen")
public interface MetaCodeGenAPI {

    /**
     * 生成建表语句
     */
    @ApiOperation(value = "生成建表语句")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", dataType = "int", value = "项目id", paramType = "query"),
    })
    void genSql(Integer projectId, HttpServletResponse response);

    /**
     * 生成代码压缩包
     */
    @ApiOperation(value = "生成代码压缩包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", dataType = "int", value = "项目id", paramType = "query"),
    })
    void genCode(Integer projectId, HttpServletResponse response);

    /**
     * sql预览
     */
    @ApiOperation(value = "sql预览")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "entityId", dataType = "int", value = "实体id", paramType = "query"),
    })
    String sqlPreview(Integer entityId);

    /**
     * 提交到仓库
     */
    @ApiOperation(value = "提交到仓库")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "projectId", dataType = "int", value = "项目id", paramType = "query"),
    })
    String gitCommit(Integer entityId);
}
