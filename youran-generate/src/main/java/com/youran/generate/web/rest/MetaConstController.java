package com.youran.generate.web.rest;

import com.youran.common.exception.BusinessException;
import com.youran.common.pojo.vo.PageVO;
import com.youran.generate.constant.GenerateConst;
import com.youran.generate.pojo.dto.MetaConstAddDTO;
import com.youran.generate.pojo.dto.MetaConstUpdateDTO;
import com.youran.generate.pojo.mapper.MetaConstMapper;
import com.youran.generate.pojo.po.MetaConstPO;
import com.youran.generate.pojo.qo.MetaConstQO;
import com.youran.generate.pojo.vo.MetaConstListVO;
import com.youran.generate.pojo.vo.MetaConstShowVO;
import com.youran.generate.service.MetaConstService;
import com.youran.generate.web.AbstractController;
import com.youran.generate.web.api.MetaConstAPI;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>Title:【常量】控制器</p>
 * <p>Description:</p>
 * @author: cbb
 * @date: 2017/5/12
 */
@RestController
@RequestMapping(GenerateConst.API_PATH +"/meta_const")
public class MetaConstController extends AbstractController implements MetaConstAPI {

    @Autowired
    private MetaConstService metaConstService;

    @Override
    @PostMapping(value = "/save")
    public MetaConstShowVO save(@Valid @RequestBody MetaConstAddDTO metaConstAddDTO) {
        MetaConstPO metaConstPO = metaConstService.save(metaConstAddDTO);
        return MetaConstMapper.INSTANCE.toShowVO(metaConstPO);
    }

    @Override
    @PutMapping(value = "/update")
    public MetaConstShowVO update(@Valid @RequestBody MetaConstUpdateDTO metaConstUpdateDTO) {
        MetaConstPO metaConstPO = metaConstService.update(metaConstUpdateDTO);
        return MetaConstMapper.INSTANCE.toShowVO(metaConstPO);
    }

    @Override
    @GetMapping(value = "/list")
    public PageVO<MetaConstListVO> list(@Valid MetaConstQO metaConstQO) {
        PageVO<MetaConstListVO> page = metaConstService.list(metaConstQO);
        return page;
    }

    @Override
    @GetMapping(value = "/{constId}")
    public MetaConstShowVO show(@PathVariable Integer constId) {
        MetaConstShowVO metaConstShowVO = metaConstService.show(constId);
        return metaConstShowVO;
    }

    @Override
    @DeleteMapping(value = "/{constId}")
    public Integer delete(@PathVariable Integer constId) {
        int count = metaConstService.delete(constId);
        return count;
    }

    @Override
    @PutMapping(value = "deleteBatch")
    public Integer deleteBatch(@RequestBody Integer[] constId) {
        if(ArrayUtils.isEmpty(constId)){
            throw new BusinessException("参数为空");
        }
        int count = metaConstService.delete(constId);
        return count;
    }
}
