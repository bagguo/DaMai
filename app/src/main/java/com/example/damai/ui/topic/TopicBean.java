package com.example.damai.ui.topic;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by guodazhao on 2018/1/31 0031.
 * greendAO使用注解
 */
@Entity
public class TopicBean {
    @Id
    private Long id;//Long对象
    private String key;//各个bean的url
    private Long time;//超时时间


    private int i;
    private String n;
    private String p;
    private String priceName;
    private String t;
    private int s;
    private String v;
    private int VenId;
    private String VenName;
    private int b;
    private String d;
    private boolean IsOnlyXuanZuo;
    private boolean IsToBeAboutTo;
    private int CategoryID;
    private int IsXuanZuo;
    private int CityId;
    private int BuySum;
    private String StartTicketTime;
    private int PrivilegeType;
    private int openSum;
    private int IsGeneralAgent;
    private boolean IsPreregistration;
    private boolean SupportedDeductionIntegral;
    private boolean isSellOut;

    @Generated(hash = 1654964541)
    public TopicBean(Long id, String key, Long time, int i, String n, String p,
            String priceName, String t, int s, String v, int VenId, String VenName,
            int b, String d, boolean IsOnlyXuanZuo, boolean IsToBeAboutTo,
            int CategoryID, int IsXuanZuo, int CityId, int BuySum,
            String StartTicketTime, int PrivilegeType, int openSum,
            int IsGeneralAgent, boolean IsPreregistration,
            boolean SupportedDeductionIntegral, boolean isSellOut) {
        this.id = id;
        this.key = key;
        this.time = time;
        this.i = i;
        this.n = n;
        this.p = p;
        this.priceName = priceName;
        this.t = t;
        this.s = s;
        this.v = v;
        this.VenId = VenId;
        this.VenName = VenName;
        this.b = b;
        this.d = d;
        this.IsOnlyXuanZuo = IsOnlyXuanZuo;
        this.IsToBeAboutTo = IsToBeAboutTo;
        this.CategoryID = CategoryID;
        this.IsXuanZuo = IsXuanZuo;
        this.CityId = CityId;
        this.BuySum = BuySum;
        this.StartTicketTime = StartTicketTime;
        this.PrivilegeType = PrivilegeType;
        this.openSum = openSum;
        this.IsGeneralAgent = IsGeneralAgent;
        this.IsPreregistration = IsPreregistration;
        this.SupportedDeductionIntegral = SupportedDeductionIntegral;
        this.isSellOut = isSellOut;
    }

    @Generated(hash = 1961217991)
    public TopicBean() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public int getVenId() {
        return VenId;
    }

    public void setVenId(int venId) {
        VenId = venId;
    }

    public String getVenName() {
        return VenName;
    }

    public void setVenName(String venName) {
        VenName = venName;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public boolean isOnlyXuanZuo() {
        return IsOnlyXuanZuo;
    }

    public void setOnlyXuanZuo(boolean onlyXuanZuo) {
        IsOnlyXuanZuo = onlyXuanZuo;
    }

    public boolean isToBeAboutTo() {
        return IsToBeAboutTo;
    }

    public void setToBeAboutTo(boolean toBeAboutTo) {
        IsToBeAboutTo = toBeAboutTo;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getIsXuanZuo() {
        return IsXuanZuo;
    }

    public void setIsXuanZuo(int isXuanZuo) {
        IsXuanZuo = isXuanZuo;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public int getBuySum() {
        return BuySum;
    }

    public void setBuySum(int buySum) {
        BuySum = buySum;
    }

    public String getStartTicketTime() {
        return StartTicketTime;
    }

    public void setStartTicketTime(String startTicketTime) {
        StartTicketTime = startTicketTime;
    }

    public int getPrivilegeType() {
        return PrivilegeType;
    }

    public void setPrivilegeType(int privilegeType) {
        PrivilegeType = privilegeType;
    }

    public int getOpenSum() {
        return openSum;
    }

    public void setOpenSum(int openSum) {
        this.openSum = openSum;
    }

    public int getIsGeneralAgent() {
        return IsGeneralAgent;
    }

    public void setIsGeneralAgent(int isGeneralAgent) {
        IsGeneralAgent = isGeneralAgent;
    }

    public boolean isPreregistration() {
        return IsPreregistration;
    }

    public void setPreregistration(boolean preregistration) {
        IsPreregistration = preregistration;
    }

    public boolean isSupportedDeductionIntegral() {
        return SupportedDeductionIntegral;
    }

    public void setSupportedDeductionIntegral(boolean supportedDeductionIntegral) {
        SupportedDeductionIntegral = supportedDeductionIntegral;
    }

    public boolean isSellOut() {
        return isSellOut;
    }

    public void setSellOut(boolean sellOut) {
        isSellOut = sellOut;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsOnlyXuanZuo() {
        return this.IsOnlyXuanZuo;
    }

    public void setIsOnlyXuanZuo(boolean IsOnlyXuanZuo) {
        this.IsOnlyXuanZuo = IsOnlyXuanZuo;
    }

    public boolean getIsToBeAboutTo() {
        return this.IsToBeAboutTo;
    }

    public void setIsToBeAboutTo(boolean IsToBeAboutTo) {
        this.IsToBeAboutTo = IsToBeAboutTo;
    }

    public boolean getIsPreregistration() {
        return this.IsPreregistration;
    }

    public void setIsPreregistration(boolean IsPreregistration) {
        this.IsPreregistration = IsPreregistration;
    }

    public boolean getSupportedDeductionIntegral() {
        return this.SupportedDeductionIntegral;
    }

    public boolean getIsSellOut() {
        return this.isSellOut;
    }

    public void setIsSellOut(boolean isSellOut) {
        this.isSellOut = isSellOut;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
