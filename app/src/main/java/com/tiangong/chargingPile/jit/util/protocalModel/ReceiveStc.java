package com.tiangong.chargingPile.jit.util.protocalModel;

/**
 * Created by mac on 2017/10/27.
 */
public class ReceiveStc {

    /***=====================STC数据**************************/
    private String chmHex;//stc元数据
    private double stcChm_version;//版本号

    private String bhmHex;//
    private double stcBhm_maxVol;//最高电压

    private String crmHex;
    private boolean stcCrm_state;//充电桩状态
    private long stcCrm_id;//充电桩编号
    private long stcCrm_areaId;//充电桩区域编号

    private String brmHex;
    private double stcBrm_version;//电池版本号
    private short stcBrm_type;//电池类型
    private int stcBrm_capacity; //电池容量
    private double stcBrm_vol;//电池电压

    private String bcpHex;
    private String stcBcp;//参见2015国际

    private String ctsHex;
    private String stcCts;//stc日期

    private String cmlHex;
    private double stcCml_maxVol;//最高输出电压
    private double stcCml_minVol;//最低输出电压
    private double stcCml_maxAn;//最大输出电流
    private double stcCml_minAn;//最小输出电流

    private String broHex;
    private boolean stcBro = true;

    private String croHex;
    private boolean stcCro = true;

    private String bstHex;
    private short stcBst_terminateCause;//终止原因
    private int stcBst_faultCause;//故障原因
    private short stcBst_errorCause;//错误原因

    private String cstHex;
    private short stcCst_terminateCause;//终止原因
    private int stcCst_faultCause;//故障原因
    private short stcCst_errorCause;//错误原因

    private String csdHex;
    private int stcCsd_chargeTime;//充电时间
    private int stcCsd_outputPower;//输出能量
    private long stcCsd_chargeMachineId;//充电机编号

    private String bsdHex;
    private short stcBsd_hopowerState;//喝点状态
    private int stcBsd_singleCellMinVol;//单体蓄电池最低电压
    private int stcBsd_singleCellMaxVol;//单体蓄电池最低电压
    private short stcBsd_minTemperature;//最低温度
    private short stcBsd_maxTemperature;//最高温度


    public double getStcChm_version() {
        return stcChm_version;
    }

    public void setStcChm_version(double stcChm_version) {
        this.stcChm_version = stcChm_version;
    }

    public double getStcBhm_maxVol() {
        return stcBhm_maxVol;
    }

    public void setStcBhm_maxVol(double stcBhm_maxVol) {
        this.stcBhm_maxVol = stcBhm_maxVol;
    }

    public boolean isStcCrm_state() {
        return stcCrm_state;
    }

    public void setStcCrm_state(boolean stcCrm_state) {
        this.stcCrm_state = stcCrm_state;
    }

    public long getStcCrm_id() {
        return stcCrm_id;
    }

    public void setStcCrm_id(long stcCrm_id) {
        this.stcCrm_id = stcCrm_id;
    }

    public long getStcCrm_areaId() {
        return stcCrm_areaId;
    }

    public void setStcCrm_areaId(long stcCrm_areaId) {
        this.stcCrm_areaId = stcCrm_areaId;
    }

    public double getStcBrm_version() {
        return stcBrm_version;
    }

    public void setStcBrm_version(double stcBrm_version) {
        this.stcBrm_version = stcBrm_version;
    }

    public short getStcBrm_type() {
        return stcBrm_type;
    }

    public void setStcBrm_type(short stcBrm_type) {
        this.stcBrm_type = stcBrm_type;
    }

    public int getStcBrm_capacity() {
        return stcBrm_capacity;
    }

    public void setStcBrm_capacity(int stcBrm_capacity) {
        this.stcBrm_capacity = stcBrm_capacity;
    }

    public double getStcBrm_vol() {
        return stcBrm_vol;
    }

    public void setStcBrm_vol(double stcBrm_vol) {
        this.stcBrm_vol = stcBrm_vol;
    }

    public String getStcBcp() {
        return stcBcp;
    }

    public void setStcBcp(String stcBcp) {
        this.stcBcp = stcBcp;
    }

    public String getStcCts() {
        return stcCts;
    }

    public void setStcCts(String stcCts) {
        this.stcCts = stcCts;
    }

    public double getStcCml_maxVol() {
        return stcCml_maxVol;
    }

    public void setStcCml_maxVol(double stcCml_maxVol) {
        this.stcCml_maxVol = stcCml_maxVol;
    }

    public double getStcCml_minVol() {
        return stcCml_minVol;
    }

    public void setStcCml_minVol(double stcCml_minVol) {
        this.stcCml_minVol = stcCml_minVol;
    }

    public double getStcCml_maxAn() {
        return stcCml_maxAn;
    }

    public void setStcCml_maxAn(double stcCml_maxAn) {
        this.stcCml_maxAn = stcCml_maxAn;
    }

    public double getStcCml_minAn() {
        return stcCml_minAn;
    }

    public void setStcCml_minAn(double stcCml_minAn) {
        this.stcCml_minAn = stcCml_minAn;
    }

    public boolean isStcBro() {
        return stcBro;
    }

    public void setStcBro(boolean stcBro) {
        this.stcBro = stcBro;
    }

    public boolean isStcCro() {
        return stcCro;
    }

    public void setStcCro(boolean stcCro) {
        this.stcCro = stcCro;
    }

    public short getStcBst_terminateCause() {
        return stcBst_terminateCause;
    }

    public void setStcBst_terminateCause(short stcBst_terminateCause) {
        this.stcBst_terminateCause = stcBst_terminateCause;
    }

    public int getStcBst_faultCause() {
        return stcBst_faultCause;
    }

    public void setStcBst_faultCause(int stcBst_faultCause) {
        this.stcBst_faultCause = stcBst_faultCause;
    }

    public short getStcBst_errorCause() {
        return stcBst_errorCause;
    }

    public void setStcBst_errorCause(short stcBst_errorCause) {
        this.stcBst_errorCause = stcBst_errorCause;
    }

    public short getStcCst_terminateCause() {
        return stcCst_terminateCause;
    }

    public void setStcCst_terminateCause(short stcCst_terminateCause) {
        this.stcCst_terminateCause = stcCst_terminateCause;
    }

    public int getStcCst_faultCause() {
        return stcCst_faultCause;
    }

    public void setStcCst_faultCause(int stcCst_faultCause) {
        this.stcCst_faultCause = stcCst_faultCause;
    }

    public short getStcCst_errorCause() {
        return stcCst_errorCause;
    }

    public void setStcCst_errorCause(short stcCst_errorCause) {
        this.stcCst_errorCause = stcCst_errorCause;
    }

    public int getStcCsd_chargeTime() {
        return stcCsd_chargeTime;
    }

    public void setStcCsd_chargeTime(int stcCsd_chargeTime) {
        this.stcCsd_chargeTime = stcCsd_chargeTime;
    }

    public int getStcCsd_outputPower() {
        return stcCsd_outputPower;
    }

    public void setStcCsd_outputPower(int stcCsd_outputPower) {
        this.stcCsd_outputPower = stcCsd_outputPower;
    }

    public long getStcCsd_chargeMachineId() {
        return stcCsd_chargeMachineId;
    }

    public void setStcCsd_chargeMachineId(long stcCsd_chargeMachineId) {
        this.stcCsd_chargeMachineId = stcCsd_chargeMachineId;
    }

    public short getStcBsd_hopowerState() {
        return stcBsd_hopowerState;
    }

    public void setStcBsd_hopowerState(short stcBsd_hopowerState) {
        this.stcBsd_hopowerState = stcBsd_hopowerState;
    }

    public int getStcBsd_singleCellMinVol() {
        return stcBsd_singleCellMinVol;
    }

    public void setStcBsd_singleCellMinVol(int stcBsd_singleCellMinVol) {
        this.stcBsd_singleCellMinVol = stcBsd_singleCellMinVol;
    }

    public int getStcBsd_singleCellMaxVol() {
        return stcBsd_singleCellMaxVol;
    }

    public void setStcBsd_singleCellMaxVol(int stcBsd_singleCellMaxVol) {
        this.stcBsd_singleCellMaxVol = stcBsd_singleCellMaxVol;
    }

    public short getStcBsd_minTemperature() {
        return stcBsd_minTemperature;
    }

    public void setStcBsd_minTemperature(short stcBsd_minTemperature) {
        this.stcBsd_minTemperature = stcBsd_minTemperature;
    }

    public short getStcBsd_maxTemperature() {
        return stcBsd_maxTemperature;
    }

    public void setStcBsd_maxTemperature(short stcBsd_maxTemperature) {
        this.stcBsd_maxTemperature = stcBsd_maxTemperature;
    }

    public String getChmHex() {
        return chmHex;
    }

    public void setChmHex(String chmHex) {
        this.chmHex = chmHex;
    }

    public String getBhmHex() {
        return bhmHex;
    }

    public void setBhmHex(String bhmHex) {
        this.bhmHex = bhmHex;
    }

    public String getCrmHex() {
        return crmHex;
    }

    public void setCrmHex(String crmHex) {
        this.crmHex = crmHex;
    }

    public String getBrmHex() {
        return brmHex;
    }

    public void setBrmHex(String brmHex) {
        this.brmHex = brmHex;
    }

    public String getBcpHex() {
        return bcpHex;
    }

    public void setBcpHex(String bcpHex) {
        this.bcpHex = bcpHex;
    }

    public String getCtsHex() {
        return ctsHex;
    }

    public void setCtsHex(String ctsHex) {
        this.ctsHex = ctsHex;
    }

    public String getCmlHex() {
        return cmlHex;
    }

    public void setCmlHex(String cmlHex) {
        this.cmlHex = cmlHex;
    }

    public String getBroHex() {
        return broHex;
    }

    public void setBroHex(String broHex) {
        this.broHex = broHex;
    }

    public String getCroHex() {
        return croHex;
    }

    public void setCroHex(String croHex) {
        this.croHex = croHex;
    }

    public String getBstHex() {
        return bstHex;
    }

    public void setBstHex(String bstHex) {
        this.bstHex = bstHex;
    }

    public String getCstHex() {
        return cstHex;
    }

    public void setCstHex(String cstHex) {
        this.cstHex = cstHex;
    }

    public String getCsdHex() {
        return csdHex;
    }

    public void setCsdHex(String csdHex) {
        this.csdHex = csdHex;
    }

    public String getBsdHex() {
        return bsdHex;
    }

    public void setBsdHex(String bsdHex) {
        this.bsdHex = bsdHex;
    }
}
