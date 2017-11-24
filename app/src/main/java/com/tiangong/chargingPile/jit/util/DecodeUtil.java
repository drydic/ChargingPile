package com.tiangong.chargingPile.jit.util;


import android.util.Log;

import com.tiangong.chargingPile.jit.util.protocalModel.ReceiveCurrentData;
import com.tiangong.chargingPile.jit.util.protocalModel.ReceiveStd;
import com.tiangong.chargingPile.model.ChargingPaileData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mac on 2017/10/26.
 */
public class DecodeUtil {


    public static void decode(byte[] read, final ReceiveCurrentData model){

        if(read==null||read.length!=16){
            throw new RuntimeException("来自板子上的数据长度不是16个字节");
        }

        String hexStr = convertToHexStr(read);

        switch (read[0] & 0xff){

            case 0x06:
                ChargingPaileData.getInstance().setStop(true);
                ChargingPaileData.getInstance().setChargingState("检测结束");
                Log.i("tag", "decode: =================>0x06");
                break;
            case 0x11:
                ChargingPaileData.getInstance().setChargingState("正在检测\n握手启动阶段");;
                break;
            case 0x01: //通信状态;
                if((read[1] & 0xff) == 0xaa){
                    model.getSta().setSta_state(true);
                    ChargingPaileData.getInstance().setTestState("充电枪已插入");
                    if (ChargingPaileData.getInstance().isFirstAA()) {
                        ChargingPaileData.getInstance().setCanClick(true);
                        ChargingPaileData.getInstance().setFirstAA(false);
                    }

                    //0x01aa 显示充电桩已插入状态
                }else if ((read[1] & 0xff) == 0x00){
                }
                else{
                    model.getSta().setSta_state(false);
                }
                //握手阶段
                ChargingPaileData.getInstance().setHandTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));

                break;
            case 0x02: //CAN状态回传
                short canByte =(short)(read[1] & 0xff);
                if(canByte == 0x26){ //版本号
                    model.getStc().setStcChm_version((byteToShort_HL(read,4) & 0xffff)+(read[3] & 0xff)/10.0);
                    model.getStc().setChmHex(hexStr);
                }
                if(canByte == 0x27){ //最高电压
                    model.getStc().setStcBhm_maxVol((byteToShort_HL(read,3) & 0xffff) /10.0);
                    model.getStc().setBhmHex(hexStr);
                }
                if(canByte == 0x01){ //CRM

                    //一旦受到CRM 显示握手辨识阶段
                    ChargingPaileData.getInstance().setChargingState("正在检测：\n握手辨识阶段");
                    model.getStc().setStcCrm_state((read[3] & 0xff) == 0xaa);
                    model.getStc().setStcCrm_id(byteToInt_HL(read,4) & 0xffffffff);
                    //TODO 规约为三个字节编码，此处不知该怎么处理
                    model.getStc().setStcCrm_areaId(byteToShort_HL(read,8) & 0xffff);
                    model.getStc().setCrmHex(hexStr);
                }
                if(canByte == 0x02){ //BRM
                    model.getStc().setStcBrm_version((byteToShort_HL(read,4) & 0xffff)+(read[3] & 0xff)/10.0);
                    model.getStc().setStcBrm_type((short)(read[6] & 0xff));
                    model.getStc().setStcBrm_capacity(byteToShort_HL(read,7) & 0xffff);
                    model.getStc().setStcBrm_vol((byteToShort_HL(read,7) & 0xffff)/10.0);
                    model.getStc().setBrmHex(hexStr);
                }

                //TODO 参见2015国标
                if(canByte == 0x06){ //BCP
                    model.getStc().setBcpHex(hexStr);
                }
                if (canByte == 0x26){
                    ChargingPaileData.getInstance().setChargingState("握手启动阶段");
                }

                if(canByte == 0x07){ //CTS
                    model.getStc().setStcCts( byteToShort_HL(read,8)+"-"+read[7]+"-"+read[6]+" "+read[5]+":"+read[4]+":"+read[3]);
                    model.getStc().setCtsHex(hexStr);
                }

                if(canByte == 0x08){ //CML
                    model.getStc().setStcCml_maxVol(byteToShort_HL(read,3)*0.1);
                    model.getStc().setStcCml_minVol(byteToShort_HL(read,5)*0.1);
                    model.getStc().setStcCml_maxAn(400-byteToShort_HL(read,7)*0.1);
                    model.getStc().setStcCml_minAn(400-byteToShort_HL(read,9)*0.1);
                    model.getStc().setCmlHex(hexStr);
                }

                if(canByte == 0x09){ //BRO
                    model.getStc().setStcBro((read[3] & 0xff) == 0xaa);
                    model.getStc().setBroHex(hexStr);
                }


                if(canByte == 0x0a){ //CRO
                    model.getStc().setStcCro((read[3] & 0xff) == 0xaa);
                    model.getStc().setCroHex(hexStr);
                }


                if(canByte == 0x19){ //BST
                    ChargingPaileData.getInstance().setChargingState("充电结束阶段");
                    model.getStc().setStcBst_terminateCause((short)(read[3] & 0xff));
                    model.getStc().setStcBst_faultCause(byteToShort_HL(read,4) & 0xffff);
                    model.getStc().setStcBst_errorCause((short)(read[6] & 0xff));
                    model.getStc().setBstHex(hexStr);
                }

                if(canByte == 0x1A){ //CST
                    ChargingPaileData.getInstance().setChargingState("充电结束阶段");
                    model.getStc().setStcCst_terminateCause((short)(read[3] & 0xff));
                    model.getStc().setStcCst_faultCause(byteToShort_HL(read,4) & 0xffff);
                    model.getStc().setStcCst_errorCause((short)(read[6] & 0xff));
                    model.getStc().setCstHex(hexStr);
                }

                if(canByte == 0x1D){ //CSD
                    ChargingPaileData.getInstance().setChargingState("充电结束阶段");
                    model.getStc().setStcCsd_chargeTime(byteToShort_HL(read,3) & 0xffff);
                    model.getStc().setStcCsd_outputPower(byteToShort_HL(read,5) & 0xffff);
                    model.getStc().setStcCsd_chargeMachineId(byteToInt_HL(read,7) & 0xffffff);
                    model.getStc().setCsdHex(hexStr);
                }


                if(canByte == 0x1C){ //BSD
                    ChargingPaileData.getInstance().setChargingState("充电结束阶段");
                    model.getStc().setStcBsd_hopowerState((short)(read[3] & 0xff));
                    model.getStc().setStcBsd_singleCellMinVol(byteToShort_HL(read,4) & 0xffff);
                    model.getStc().setStcBsd_singleCellMaxVol(byteToShort_HL(read,6) & 0xffff);
                    model.getStc().setStcBsd_maxTemperature((short)(read[8] & 0xff));
                    model.getStc().setStcBsd_minTemperature((short)(read[9] & 0xff));
                    model.getStc().setBsdHex(hexStr);
                }

                break;

            case 0x04: //STD状态回传
                ChargingPaileData.getInstance().setChargingState("正在检测:\n充电阶段");
                if (model.getStdList().size()==0){
                    ChargingPaileData.getInstance().setChargTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));

                }
                ReceiveStd std = new ReceiveStd();
                std.setStd_measureVol(byteToShort_HL(read,1)*0.1);
                std.setStd_measureAn(400-byteToShort_HL(read,3)*0.1);
                std.setStd_chargeTime((short)(read[5] & 0xff));
                std.setStd_realMeasureVol(byteToShort_HL(read,6)*0.1);
                std.setStd_realMeasureAn(400-byteToShort_HL(read,8)*0.1);
                std.setStd_realMeasureWaveVol(byteToShort_HL(read,10)*0.1);
                std.setStd_chargeNeedVol(byteToShort_HL(read,12)*0.1);

                model.setCurrentStd(std);
                model.getStdList().add(std);

                break;

        }

    }


    public static void shortToByte_LH(short shortVal, byte[] b, int offset) {
        b[0 + offset] = (byte) (shortVal & 0xff);
        b[1 + offset] = (byte) (shortVal >> 8 & 0xff);
    }


    //byte 高低位转换 为short
    public static short byteToShort_HL(byte[] b, int offset)
    {
        short result;
        result = (short)((((b[offset + 1]) << 8) & 0xff00 | b[offset] & 0x00ff));
        return result;
    }

    public static void intToByte_LH(int intVal, byte[] b, int offset) {
        b[0 + offset] = (byte) (intVal & 0xff);
        b[1 + offset] = (byte) (intVal >> 8 & 0xff);
        b[2 + offset] = (byte) (intVal >> 16 & 0xff);
        b[3 + offset] = (byte) (intVal >> 24 & 0xff);
    }

    public static int byteToInt_HL(byte[] b, int offset)
    {
        int result;
        result = (((b[3 + offset] & 0x00ff) << 24) & 0xff000000)
                | (((b[2 + offset] & 0x00ff) << 16) & 0x00ff0000)
                | (((b[1 + offset] & 0x00ff) << 8) & 0x0000ff00)
                | ((b[0 + offset] & 0x00ff));
        return result;
    }


    public static String convertToHexStr(byte[] bts){
        StringBuilder sb  = new StringBuilder();
        for(byte bt : bts){
            if((bt & 0xff)<= 0x0f){
                sb.append("0"+Integer.toHexString(bt & 0xff)+" ");
            }else{
                sb.append(Integer.toHexString(bt & 0xff)+" ");
            }
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println( -127 & 0xff );
    }

}
