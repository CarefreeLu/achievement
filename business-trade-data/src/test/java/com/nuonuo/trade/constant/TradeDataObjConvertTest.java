package com.nuonuo.trade.constant;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.nuonuo.trade.entity.TradeDataTaxiDB;
import com.nuonuo.trade.model.*;
import com.nuonuo.trade.util.CryptoUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/7 14:43
 */
public class TradeDataObjConvertTest
{
    @Test
    public void encrypt() throws Exception
    {
        String data = "AH95F820190701121154";
        String key = "12345678abcdefgh";
        data = CryptoUtils.encrypt(data, key);
        System.out.println(data);
    }

    @Test
    public void convertTradeData4TaxiView(){
        TradeData4Taxi tradeData = buildTradeData4Taxi();

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("createOwnershipFlag");
        TradeData4TaxiView view = JSONObject.parseObject(JSONObject.toJSONString(tradeData), TradeData4TaxiView.class);
        BusinessTradeData<TradeData4TaxiView> data = new BusinessTradeData<>();
        data.setBusinessType(BusinessTypeE.TAXI.code);
        data.setBusinessPlatformId("001");
        data.setBusinessTradeData(view);
        System.out.println(JSONObject.toJSONString(data));
        System.out.println(view);
        System.out.println(JSONObject.toJSONString(view, filter));
        List<TradeData4TaxiView> views = new ArrayList<>();
        views.add(view);
        views.add(view);
        System.out.println(JSONObject.toJSONString(views));

    }

    @Test
    public void taxiTradeDataConvert()
    {
        TradeData4Taxi tradeData = buildTradeData4Taxi();

        BusinessTradeData<TradeData4Taxi> data = new BusinessTradeData<>();
        data.setBusinessType(BusinessTypeE.TAXI.code);
        data.setBusinessPlatformId("001");
        data.setBusinessTradeData(tradeData);

        ResponseObj<BusinessTradeData<TradeData4Taxi>> responseObj = ResponseObj.buildSuccessResponse("成功", data);

        String result = JSONObject.toJSONString(responseObj);
        System.out.println(result);
        RequestParamTradeData requestParamTradeData = new RequestParamTradeData();
        requestParamTradeData.setTradeNo("1");
        requestParamTradeData.setBusinessPlatformId("001");
        requestParamTradeData.setBusinessType("001");

        TradeData4Taxi resultObj = parseTradeData(result, BusinessPlatformE.TAXI_ZHENGSHANG.clazz);
        Object taxiTradeData = resultObj;
        System.out.println(taxiTradeData);
    }

    private <T> T parseTradeData(String result, Class clazz){
        ResponseObj<BusinessTradeData<T>> tradeData = JSONObject.parseObject(result, new TypeReference<ResponseObj<BusinessTradeData<T>>>(clazz){});
        return tradeData.getResultBody().getBusinessTradeData();
    }

    @Test
    public void tradeDataTaxiDBConvert()
    {
        TradeData4Taxi tradeData = buildTradeData4Taxi();

        String strJson = JSONObject.toJSONString(tradeData);

        TradeDataTaxiDB tradeDataTaxiDB = JSONObject.parseObject(strJson, TradeDataTaxiDB.class);
        System.out.println(tradeDataTaxiDB);
        String tradeDataTaxiDBJson = JSONObject.toJSONString(tradeDataTaxiDB);
        System.out.println(tradeDataTaxiDBJson);
        System.out.println(JSONObject.parseObject(tradeDataTaxiDBJson, TradeData4Taxi.class));
    }

    @Test
    public void Site()
    {
        Site startStie = new Site();
        startStie.setProvince(null);
        startStie.setCity("");
        startStie.setDistrict("");
        startStie.setTownship("");
        startStie.setAddress("");
        System.out.println(JSONObject.toJSONString(startStie));
    }

    private TradeData4Taxi buildTradeData4Taxi()
    {
        TradeData4Taxi tradeData = new TradeData4Taxi();
        tradeData.setTradeNo("AH95F820190701121121");
        tradeData.setPayeeTaxNo("339901999999142");
        tradeData.setAmount("29");
        tradeData.setLicensePlateNo("浙AH95F8");
        tradeData.setDeviceNo("EFW1324535677");
        tradeData.setStartTime("20190701121151");
        tradeData.setEndTime("20190701125551");
        Site startStie = new Site();
        startStie.setProvince("浙江省");
        startStie.setCity("杭州市");
        startStie.setDistrict("西湖区");
        startStie.setTownship("蒋村街道");
        startStie.setAddress("双龙街金色西溪");
        Site endSite = new Site();
        endSite.setProvince("浙江省");
        endSite.setCity("杭州市");
        endSite.setDistrict("西湖区");
        endSite.setTownship("蒋村街道");
        endSite.setAddress("西溪银泰");
        tradeData.setStartSite(startStie);
        tradeData.setEndSite(endSite);
        tradeData.setUnitPrice("2.5");
        tradeData.setMileage("21.34");
        tradeData.setTravelStatus("1");
        tradeData.setStartLongitude("104.07642");
        tradeData.setStartLatitude("38.6518");
        tradeData.setStartAzimuth("166.324267");
        tradeData.setEndLongitude("104.17642");
        tradeData.setEndLatitude("38.7518");
        tradeData.setEndAzimuth("16.324267");
        tradeData.setWaitingTime("126");

        return tradeData;
    }
}
