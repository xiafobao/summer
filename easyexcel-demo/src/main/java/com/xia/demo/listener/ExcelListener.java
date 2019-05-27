package com.xia.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.xia.demo.model.GymData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T extends BaseRowModel> extends AnalysisEventListener<T> {
    private final List<T> rows = new ArrayList<>();
    private final List<T> problemRows = new ArrayList<>();

    @Override
    public void invoke(T object, AnalysisContext context) {
        GymData gymData = (GymData)object;
        if(StringUtils.isAnyBlank(gymData.getName(), gymData.getLatitude(), gymData.getLongitude(), gymData.getAddress(), gymData.getPhone())){
            problemRows.add(object);
        }else{
            rows.add(object);

        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.printf("数据总量为: %d 条", (rows.size()+problemRows.size()));
        System.out.println();
        System.out.printf("有问题的数据有：%d 条", problemRows.size());
        System.out.println();
        if (CollectionUtils.isNotEmpty(problemRows)) {
            int phoneIsNull = 0;
            int nameIsNull = 0;
            int latitudeIsNull = 0;
            int longitudeIsNull = 0;
            int addressIsNull = 0;
            List<GymData> problemLsit = (List<GymData>) problemRows;
            for (GymData e : problemLsit) {
                if(StringUtils.isBlank(e.getPhone())){
                    phoneIsNull++;
                }
                if(StringUtils.isBlank(e.getName())){
                    nameIsNull++;
                }
                if(StringUtils.isBlank(e.getLatitude())){
                    latitudeIsNull++;
                }
                if(StringUtils.isBlank(e.getLongitude())){
                    longitudeIsNull++;
                }
                if(StringUtils.isBlank(e.getAddress())){
                    addressIsNull++;
                }
            }
            System.out.println();
            System.out.printf("名称数据为空的有：%d 条", nameIsNull);
            System.out.println();
            System.out.printf("经度数据为空的有：%d 条", longitudeIsNull);
            System.out.println();
            System.out.printf("纬度数据为空的有：%d 条", latitudeIsNull);
            System.out.println();
            System.out.printf("手机号码数据为空的有：%d 条", phoneIsNull);
            System.out.println();
            System.out.printf("地址数据为空的有：%d 条", addressIsNull);
            System.out.println();
        }
    }

    public List<T> getRows() {
        return rows;
    }

    public List<T> getProblemRows() {
        return problemRows;
    }
}