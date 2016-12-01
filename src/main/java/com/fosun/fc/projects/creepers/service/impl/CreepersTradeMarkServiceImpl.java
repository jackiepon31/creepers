package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersTradeMarkDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersTradeMarkDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;
import com.fosun.fc.projects.creepers.pageprocessor.TradeMarkProcessor;
import com.fosun.fc.projects.creepers.pipeline.TradeMarkPipline;
import com.fosun.fc.projects.creepers.service.ICreepersTradeMarkService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Spider;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author MaXin
 * @since 2016-8-11 18:30:28
 * @see
 */
@Service
@Transactional
public class CreepersTradeMarkServiceImpl implements ICreepersTradeMarkService {

    @Autowired
    private CreepersTradeMarkDao creepersTradeMarkDao;

    @Autowired
    private TradeMarkProcessor tradeMarkProcessor;

    @Autowired
    private TradeMarkPipline tradeMarkPipline;

    @Override
    public List<TCreepersTradeMark> findByMerName(String merName) {
        return creepersTradeMarkDao.findByMerName(merName);
    }

    @Override
    public void processByMerName(String merName) {
        // 初始化Param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.TRADE_MARK_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        String encodeName = CommonMethodUtils.toUtf8StringWith(merName, "%25");
        // 启动爬虫
        Spider.create(tradeMarkProcessor).addPipeline(tradeMarkPipline)
                .setDownloader(new HttpRequestDownloader().setParam(param)).thread(10)
                .addUrl("http://sbcx.saic.gov.cn:9080/tmois/wszhcx_getLikeCondition.xhtml?appCnName="
                        + encodeName + "&intCls=&paiType=0")
                .run();

    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CreepersTradeMarkDTO> queryTradeMarkList(Map<String, Object> searchParams, int pageNumber, int pageSize,
            String sortType) throws Exception {

        PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<TCreepersTradeMark> spec = (Specification<TCreepersTradeMark>) CommonMethodUtils
                .buildSpecification(searchParams, "Dt");
        Page<TCreepersTradeMark> tradeMarkPage = creepersTradeMarkDao.findAll(spec, pageable);
        List<TCreepersTradeMark> tradeMarkList = tradeMarkPage.getContent();
        List<CreepersTradeMarkDTO> tradeMarkDTOList = new ArrayList<CreepersTradeMarkDTO>();
        tradeMarkDTOList = BeanMapper.mapList(tradeMarkList, CreepersTradeMarkDTO.class);
        Page<CreepersTradeMarkDTO> resultPage = new PageImpl<CreepersTradeMarkDTO>(
                new ArrayList<CreepersTradeMarkDTO>(tradeMarkDTOList), pageable, tradeMarkPage.getTotalElements());

        return resultPage;
    }
    
    @Override
    public void deleteByMerName(String merName) {

        creepersTradeMarkDao.deleteByMerName(merName);
    }
    
    @Override
    public void saveEntity(TCreepersTradeMark entity) {
        creepersTradeMarkDao.saveAndFlush(entity);
    }
    
    @Override
    public void saveEntity(List<TCreepersTradeMark> entityList) {
        for(TCreepersTradeMark entity:entityList)
        creepersTradeMarkDao.saveAndFlush(entity);
    }


}
