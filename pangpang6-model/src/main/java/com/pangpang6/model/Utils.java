package com.pangpang6.model;

import com.google.common.collect.Lists;
import com.pangpang6.model.entity.SecOfflineWorksheetitem;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiangjg on 2017/8/16.
 */
public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String MYSQL_DOT = "`";
    public static String SQLSERVER_DOT = "\"";
    public static String AND = "AND";

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String NET_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.S'+08:00'";

    private static final String UNKNOWN_DATE_TIME = "/Date(-62135596800000+0800)/";
    private static final String UNKNOWN_DATE_TIME_JAVA = "-62135596800000";

    private static final String UNKONW_DATE_COVERT_FORMAT = "1970-01-01 00:00:00.000";

    public static FastDateFormat SDF = FastDateFormat.getInstance(DEFAULT_DATE_FORMAT);
    public static FastDateFormat DEFAUT_SDF = FastDateFormat.getInstance("yyy-MM-dd");
    public static FastDateFormat SDF_NET = FastDateFormat.getInstance(NET_DATE_FORMAT);

    private Utils() {
        throw new AssertionError();
    }

    public static String concatWhereCause(String dot, Map<String, String> criteria) {
        String where = " ";
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                where = where.concat(AND + " " + dot + entry.getKey() + dot + "= ? ");
            }
        }
        return where;
    }

    public static String formatInCause(List<? extends Object> lists) {
        String in = "";
        for (Object item : lists) {
            in = in.concat(" ? ,");
        }
        if (StringUtils.isNotBlank(in)) {
            in = "(" + in.substring(0, in.lastIndexOf(",")) + " )";
        }
        return in;
    }

    /**
     * 不区分大小写实现map.get(key)
     *
     * @param map
     * @param key
     * @return
     */
    public static Object getMapIgnorCase(Map<String, Object> map, String key) {

        Set<String> keySet = map.keySet();
        for (String tmpKey : keySet) {
            if (tmpKey.equalsIgnoreCase(key)) {
                return map.get(tmpKey);
            }
        }

        return null;
    }

    /**
     * 按照不同的比较类型比较
     *
     * @param orderValue
     * @param baseValue
     * @param matchType
     * @return
     */
    public static Boolean checkModeCondition(String orderValue, String baseValue, String matchType) {

        logger.info("checking matchType=" + matchType + ", orderValue=" + orderValue + ", baseValue=" + baseValue);

		/*if (null == orderValue) {
            if (matchType.equals(ModeMatchType.Equal.getIndex()) && null==baseValue) {
				return true;
			}
			else if (matchType.equals(ModeMatchType.NotEqual.getIndex()) && null!=baseValue) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (null == baseValue) {
			if (matchType.equals(ModeMatchType.Equal.getIndex()) && null==orderValue) {
				return true;
			}
			else if (matchType.equals(ModeMatchType.NotEqual.getIndex()) && null!=orderValue) {
				return true;
			}
			else {
				return false;
			}
		}*/
        if (null == orderValue)
            orderValue = "";
        if (null == baseValue)
            baseValue = "";

        BigDecimal orderValueDecimal = null;
        BigDecimal baseValueDecimal = null;

        switch (matchType) {
            case "EQ":
                return orderValue.equals(baseValue);
            case "NE":
                return !orderValue.equals(baseValue);
            case "GREAT":
                orderValueDecimal = new BigDecimal(orderValue);
                baseValueDecimal = new BigDecimal(baseValue);
                return orderValueDecimal.compareTo(baseValueDecimal) > 0;
            case "GE":
                orderValueDecimal = new BigDecimal(orderValue);
                baseValueDecimal = new BigDecimal(baseValue);
                return orderValueDecimal.compareTo(baseValueDecimal) >= 0;
            case "LESS":
                orderValueDecimal = new BigDecimal(orderValue);
                baseValueDecimal = new BigDecimal(baseValue);
                return orderValueDecimal.compareTo(baseValueDecimal) < 0;
            case "LE":
                orderValueDecimal = new BigDecimal(orderValue);
                baseValueDecimal = new BigDecimal(baseValue);
                return orderValueDecimal.compareTo(baseValueDecimal) <= 0;
            case "LLIKE":
                return orderValue.endsWith(baseValue);
            case "RLIKE":
                return orderValue.startsWith(baseValue);
            case "IN":
                return orderValue.contains(baseValue);
            case "NA":
                return !orderValue.contains(baseValue);
            case "REGEX":
                return Pattern.matches(baseValue, orderValue);
            default:
                logger.error("unsupported match type!");
                return false;
        }
    }

    /**
     * 从itemList获取key对应的值，key不区分大小写
     *
     * @param worksheetitemList
     * @param key
     * @return
     */
    public static String getValueFromItemList(List<SecOfflineWorksheetitem> worksheetitemList, String key) {

        //TODO:由于itemconfig和item表都加上了keyPath字段，所以这个方法需要相适应就行改造，目前这样只能取到多个相同key记录的第一个数据
        for (SecOfflineWorksheetitem worksheetitem : worksheetitemList) {
            if (worksheetitem.getKeyString().equalsIgnoreCase(key)) {
                logger.warn("返回value 为：" + worksheetitem.getKeyValue() + "!");
                return worksheetitem.getKeyValue();
            }
        }

        return null;
    }

    /**
     * 从map里面查找keyPath路径的值
     *
     * @param dataMap
     * @param keyPath eg: path.to.key or key
     * @return
     */
    public static Object getValueFromMap(Map<String, Object> dataMap, String keyPath) {

        logger.info("getValueFromMap by path : " + keyPath);

        Object tmpObj = null;
        String[] pathStrings = keyPath.split("\\.", 2);
        tmpObj = Utils.getMapIgnorCase(dataMap, pathStrings[0].trim());

        //路径消耗完，找到要找的值
        if (pathStrings.length == 1) {
            logger.info("target value : " + tmpObj);
            return tmpObj;
        }

        if (tmpObj instanceof Map) {
            logger.info("object is a Map : " + tmpObj);
            return getValueFromMap((Map) tmpObj, pathStrings[1].trim());
        } else if (tmpObj instanceof List) {
            logger.info("object is a List : " + tmpObj);
            return getValueFromList((List) tmpObj, pathStrings[1].trim());//此处决定了list的keyPath一定没有消耗完
        } else if (tmpObj instanceof Object[]) {
            logger.info("object is a Array : " + tmpObj);
            return getValueFromList(Lists.newArrayList((Object[]) tmpObj), pathStrings[1].trim());//此处决定了list的keyPath一定没有消耗完
        } else {
            logger.info("drop invalid object : " + tmpObj);
            logger.info("target value not found!");
            return null;
        }
    }

    private static List getValueFromList(List dataList, String keyPath) {

        Object tmpValue = null;
        List resultList = new ArrayList();

        for (Object listObj : dataList) {

            if (listObj instanceof Map) {
                tmpValue = getValueFromMap((Map) listObj, keyPath);
                if (null != tmpValue) {
                    logger.info("add value into list : " + tmpValue);
                    resultList.add(tmpValue);
                }
            } else if (listObj instanceof List) {
                tmpValue = getValueFromList((List) listObj, keyPath);//多层list，不消耗keyPath
                if (null != tmpValue) {
                    logger.info("add value into list : " + tmpValue);
                    resultList.add(tmpValue);
                }
            } else {
                //普通类型抛弃，因为keyPath肯定还有值，所以当前路径的普通类型的值一定不满足keyPath
                logger.info("drop invalid object : " + listObj);
            }
        }

        return resultList;
    }

    /**
     * 转换.net datetime格式为timestamp
     *
     * @param timeStr
     * @return
     */
    public static Timestamp transNetTimeToJava(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }

        if (timeStr.equals(UNKNOWN_DATE_TIME) || timeStr.equals(UNKNOWN_DATE_TIME_JAVA)) {
            return new Timestamp(0);
        }

        try {
            Date parsed = DateUtils.parseDate(timeStr, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS");
            return new Timestamp(parsed.getTime());
        } catch (Exception e) {
        }

        String TOTAL_REGEX = "^/Date\\((\\d{13})\\+\\d{4}\\)\\/";
        String TIME_REGEX = "\\d{13}";

        if (!Pattern.matches(TOTAL_REGEX, timeStr) && !Pattern.matches(TIME_REGEX, timeStr)) {
            return null;
        }

        Matcher matcher = Pattern.compile(TIME_REGEX).matcher(timeStr);
        if (!matcher.find()) {
            return null;
        }

        String targetDateString = matcher.group();
        long milliSecond = Long.valueOf(targetDateString);

        return new Timestamp(milliSecond);
    }

    public static void main(String[] args) {
        RiskEvent fact = MyJSONMapper.nonDefaultMapper().parseObject((String) "{\n" +
                        "    \"eventPoint\": \"CP0011001\",\n" +
                        "    \"orderId\": 1499749583,\n" +
                        "    \"orderType\": 1,\n" +
                        "    \"subOrderType\": 0,\n" +
                        "    \"riskLevel\": 196,\n" +
                        "    \"reqId\": 1154867993,\n" +
                        "    \"eventBody\": {\n" +
                        "        \"DealInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"CheckStatus\": 0,\n" +
                        "            \"CheckNum\": -2147483648,\n" +
                        "            \"ReferenceID\": \"\"\n" +
                        "        },\n" +
                        "        \"MainInfo\": {\n" +
                        "            \"Serverfrom\": \"fltintlint.sh.*travel.com\",\n" +
                        "            \"ClientID\": null,\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"OrderType\": 1,\n" +
                        "            \"OrderId\": 1499749583,\n" +
                        "            \"OrderDate\": \"/Date(1443274753800+0800)/\",\n" +
                        "            \"Amount\": 8046,\n" +
                        "            \"IsOnline\": \"F\",\n" +
                        "            \"CheckType\": \"1\",\n" +
                        "            \"CreateDate\": \"/Date(1443274753800+0800)/\",\n" +
                        "            \"LastCheck\": \"T\",\n" +
                        "            \"RefNo\": 0,\n" +
                        "            \"WirelessClientNo\": \"\",\n" +
                        "            \"CorporationID\": \"\",\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753803+0800)/\",\n" +
                        "            \"MerchantID\": null,\n" +
                        "            \"ProcessingType\": 1,\n" +
                        "            \"SubOrderType\": 0,\n" +
                        "            \"ApplyRemark\": null,\n" +
                        "            \"ClientVersion\": null,\n" +
                        "            \"MerchantOrderID\": null,\n" +
                        "            \"OrderProductName\": null,\n" +
                        "            \"PayExpiryDate\": \"/Date(-62135596800000)/\",\n" +
                        "            \"PreAuthorizedAmount\": -7.92281625142643E+28,\n" +
                        "            \"RiskCountrolDeadline\": \"/Date(-62135596800000)/\",\n" +
                        "            \"TotalDiscountAmount\": -7.92281625142643E+28,\n" +
                        "            \"Currency\": null,\n" +
                        "            \"OriginalAmount\": -7.92281625142643E+28,\n" +
                        "            \"IsMySelf\": -2147483648,\n" +
                        "            \"LanguageCode\": null,\n" +
                        "            \"ServiceFee\": -7.92281625142643E+28\n" +
                        "        },\n" +
                        "        \"ContactInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"ContactName\": \"郑秀媚\",\n" +
                        "            \"MobilePhone\": \"18301132339\",\n" +
                        "            \"ContactEMail\": \"\",\n" +
                        "            \"ContactTel\": \"\",\n" +
                        "            \"ContactFax\": \"\",\n" +
                        "            \"ZipCode\": null,\n" +
                        "            \"TelCall\": null,\n" +
                        "            \"ForignMobilePhone\": \"\",\n" +
                        "            \"SendTickerAddr\": \"北京朝阳区青年路西里润枫水尚4号楼18号底商  郑秀梅\",\n" +
                        "            \"PostAddress\": \"\",\n" +
                        "            \"MobilePhoneProvince\": \"北京\",\n" +
                        "            \"MobilePhoneCity\": \"北京\",\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753807+0800)/\",\n" +
                        "            \"Remark\": null\n" +
                        "        },\n" +
                        "        \"UserInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"Uid\": \"peonyzheng\",\n" +
                        "            \"UserPassword\": \"59D8687B5DF611F3DE95EC0E4D319656\",\n" +
                        "            \"SignUpDate\": \"/Date(1287270663000+0800)/\",\n" +
                        "            \"CusCharacter\": \"REPEAT\",\n" +
                        "            \"Experience\": 12896,\n" +
                        "            \"VipGrade\": 30,\n" +
                        "            \"IsTempUser\": \"F\",\n" +
                        "            \"TotalPenalty\": 0,\n" +
                        "            \"IsUidHasBlackCard\": \" \",\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753817+0800)/\",\n" +
                        "            \"BindedMobilePhone\": \"18211203045\",\n" +
                        "            \"BindedEmail\": \"peonyzheng86@hotmail.com\",\n" +
                        "            \"RelatedMobilephone\": \"18211203045\",\n" +
                        "            \"RelatedEMail\": \"irisz2012@126.com\",\n" +
                        "            \"IsBindedMobilePhone\": null,\n" +
                        "            \"IsBindedEmail\": null,\n" +
                        "            \"City\": -2147483648,\n" +
                        "            \"Address\": null,\n" +
                        "            \"Sourceid\": -2147483648,\n" +
                        "            \"IsBusinessUser\": null\n" +
                        "        },\n" +
                        "        \"IPInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"UserIPValue\": 2886753385,\n" +
                        "            \"UserIPAdd\": \"172.16.92.105\",\n" +
                        "            \"IPCountry\": \"US\",\n" +
                        "            \"IPCity\": -2147483648,\n" +
                        "            \"Continent\": -2147483648\n" +
                        "        },\n" +
                        "        \"OtherInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"OrderToSignUpDate\": 43334,\n" +
                        "            \"TakeOffToOrderDate\": 9,\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753853+0800)/\",\n" +
                        "            \"OrderInfoExternalURL\": null,\n" +
                        "            \"Bid\": null\n" +
                        "        },\n" +
                        "        \"PaymentInfo\": [\n" +
                        "            {\n" +
                        "                \"PaymentInfo\": {\n" +
                        "                    \"PaymentInfoID\": 381378314,\n" +
                        "                    \"ReqID\": 1154867993,\n" +
                        "                    \"PrepayType\": \"CCARD\",\n" +
                        "                    \"IsGuarantee\": null,\n" +
                        "                    \"Amount\": 8046,\n" +
                        "                    \"BillNo\": -9223372036854775808\n" +
                        "                },\n" +
                        "                \"CardInfoList\": [\n" +
                        "                    {\n" +
                        "                        \"PaymentInfoID\": 381378314,\n" +
                        "                        \"CardInfoID\": 259591372,\n" +
                        "                        \"CreditCardType\": 2,\n" +
                        "                        \"InfoID\": 0,\n" +
                        "                        \"CValidityCode\": \"33A0FBF4EFB2A6E196BAF857BA4AC49D\",\n" +
                        "                        \"CCardNoCode\": \"F11EBFB4742B3B3A15BDA1442070CBD3\",\n" +
                        "                        \"CardHolder\": \"                                                  \",\n" +
                        "                        \"CardBin\": \"427020\",\n" +
                        "                        \"CCardLastNoCode\": \"908075EA2C025C335F4865F7DB427062\",\n" +
                        "                        \"CCardPreNoCode\": \"24BEAA7D58245D7DC22CB773709573FB\",\n" +
                        "                        \"StateName\": \"\",\n" +
                        "                        \"BillingAddress\": \"\",\n" +
                        "                        \"Nationality\": \"  \",\n" +
                        "                        \"Nationalityofisuue\": \"  \",\n" +
                        "                        \"BankOfCardIssue\": \"\",\n" +
                        "                        \"CardBinIssue\": null,\n" +
                        "                        \"CardBinBankOfCardIssue\": null,\n" +
                        "                        \"IsForigenCard\": \"F\",\n" +
                        "                        \"ReqID\": -9223372036854775808,\n" +
                        "                        \"CardNoRefID\": 32535016,\n" +
                        "                        \"BranchCity\": null,\n" +
                        "                        \"BranchProvince\": null,\n" +
                        "                        \"IdNumberProvince\": \"广东\",\n" +
                        "                        \"IdNumberCity\": \"江门\",\n" +
                        "                        \"CreditCardNumber\": \"CTRP0001965F8B9D1749BD7972B06A0D6C2039AF8CCBB0958244913579B1146C\"\n" +
                        "                    }\n" +
                        "                ],\n" +
                        "                \"UserProfile\": null\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        \"FlightsInfo\": {\n" +
                        "            \"FlightsOrderInfo\": {\n" +
                        "                \"FlightsOrderID\": 311802539,\n" +
                        "                \"ReqID\": 1154867993,\n" +
                        "                \"TakeOffTime\": \"/Date(1443309900000+0800)/\",\n" +
                        "                \"FlightClass\": \"I\",\n" +
                        "                \"IsClient\": \" \",\n" +
                        "                \"DCity\": 1,\n" +
                        "                \"ACity\": 803,\n" +
                        "                \"DAirPort\": \"PEK\",\n" +
                        "                \"EAirPort\": \"DXB\",\n" +
                        "                \"AAirPort\": \"DUB\",\n" +
                        "                \"SubOrderType\": 0,\n" +
                        "                \"DataChange_LastTime\": \"/Date(1443274753837+0800)/\",\n" +
                        "                \"Remark\": \"\",\n" +
                        "                \"FlightCost\": 0,\n" +
                        "                \"InsuranceCost\": 0,\n" +
                        "                \"AgencyName\": null,\n" +
                        "                \"Agencyid\": 0,\n" +
                        "                \"FlightCostRate\": 0,\n" +
                        "                \"Insurance_fee\": 0,\n" +
                        "                \"Flightprice\": 0,\n" +
                        "                \"PackageAttachFee\": 0,\n" +
                        "                \"Persons\": 0,\n" +
                        "                \"Tot_Oilfee\": 0,\n" +
                        "                \"Tot_Tax\": 0,\n" +
                        "                \"Profit\": 0,\n" +
                        "                \"ACityName\": null,\n" +
                        "                \"DCityName\": null,\n" +
                        "                \"SalesType\": 4,\n" +
                        "                \"UrgencyLevel\": 0,\n" +
                        "                \"TwoPercentProfit\": 0,\n" +
                        "                \"ActualAmount\": -7.92281625142643E+28,\n" +
                        "                \"LeafletAmount\": 8046,\n" +
                        "                \"BalanceType\": null,\n" +
                        "                \"BookingChannel\": null,\n" +
                        "                \"IsEnglish\": null,\n" +
                        "                \"OnlySingleWay\": null,\n" +
                        "                \"PriceType\": null,\n" +
                        "                \"RealReservationType\": 0,\n" +
                        "                \"IsAbacus\": null,\n" +
                        "                \"TargetOrder\": 0,\n" +
                        "                \"C_Language\": null,\n" +
                        "                \"ReservationType\": 0,\n" +
                        "                \"IsPartial\": null,\n" +
                        "                \"NeedEMail\": null,\n" +
                        "                \"NeedFax\": null,\n" +
                        "                \"NeedMoreBagage\": null,\n" +
                        "                \"NeedHotel\": null,\n" +
                        "                \"TrackIDCard\": null,\n" +
                        "                \"Remittance\": null,\n" +
                        "                \"SendEmailInfo\": null,\n" +
                        "                \"SendFaxInfo\": null,\n" +
                        "                \"ExceedLimit\": null,\n" +
                        "                \"WithOtherOrders\": null,\n" +
                        "                \"WithInfantTicket\": null,\n" +
                        "                \"IsFirstCoupon\": \"F\"\n" +
                        "            },\n" +
                        "            \"PassengerInfoList\": [\n" +
                        "                {\n" +
                        "                    \"FlightsUserID\": 440011514,\n" +
                        "                    \"FlightsOrderID\": 311802539,\n" +
                        "                    \"PassengerName\": \"TANG/HAU HUNG CARREN\",\n" +
                        "                    \"PassengerNationality\": \"HK\",\n" +
                        "                    \"PassengerCardID\": \"KJ0059973\",\n" +
                        "                    \"PassengerBirthday\": \"/Date(-46944000000+0800)/\",\n" +
                        "                    \"PassengerGender\": \"F\",\n" +
                        "                    \"DataChange_LastTime\": \"/Date(1443274753840+0800)/\",\n" +
                        "                    \"PassengerCardIDType\": \"2\",\n" +
                        "                    \"PassengerAgeType\": \"ADU\",\n" +
                        "                    \"CardEndTime\": \"/Date(-62135596800000)/\",\n" +
                        "                    \"CardStartTime\": \"/Date(-62135596800000)/\",\n" +
                        "                    \"ReqId\": -9223372036854775808,\n" +
                        "                    \"SeatRemark\": null,\n" +
                        "                    \"MealRemark\": null,\n" +
                        "                    \"CredleRemark\": null,\n" +
                        "                    \"WheelChairRemark\": null\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"SegmentInfoList\": [\n" +
                        "                {\n" +
                        "                    \"FlightsOrderID\": 311802539,\n" +
                        "                    \"Sequence\": 1,\n" +
                        "                    \"DAirPort\": \"PEK\",\n" +
                        "                    \"AAirPort\": \"DXB\",\n" +
                        "                    \"SubClass\": \"W\",\n" +
                        "                    \"SeatClass\": \"Y\",\n" +
                        "                    \"DataChange_LastTime\": \"/Date(1443274753847+0800)/\",\n" +
                        "                    \"SegmentInfoID\": 306940486,\n" +
                        "                    \"DCity\": 1,\n" +
                        "                    \"ACity\": 220,\n" +
                        "                    \"Takeofftime\": \"/Date(1443309900000+0800)/\",\n" +
                        "                    \"Arrivaltime\": \"/Date(1443324900000+0800)/\",\n" +
                        "                    \"Flight\": null,\n" +
                        "                    \"IsShared\": null,\n" +
                        "                    \"PataResult\": 0,\n" +
                        "                    \"VehicleType\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"FlightsOrderID\": 311802539,\n" +
                        "                    \"Sequence\": 2,\n" +
                        "                    \"DAirPort\": \"DXB\",\n" +
                        "                    \"AAirPort\": \"DUB\",\n" +
                        "                    \"SubClass\": \"W\",\n" +
                        "                    \"SeatClass\": \"Y\",\n" +
                        "                    \"DataChange_LastTime\": \"/Date(1443274753850+0800)/\",\n" +
                        "                    \"SegmentInfoID\": 306940487,\n" +
                        "                    \"DCity\": 220,\n" +
                        "                    \"ACity\": 803,\n" +
                        "                    \"Takeofftime\": \"/Date(1443340800000+0800)/\",\n" +
                        "                    \"Arrivaltime\": \"/Date(1443358200000+0800)/\",\n" +
                        "                    \"Flight\": null,\n" +
                        "                    \"IsShared\": null,\n" +
                        "                    \"PataResult\": 0,\n" +
                        "                    \"VehicleType\": 0\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"UserProfile\": null\n" +
                        "        },\n" +
                        "        \"Order_Auth_CCard_Info\": null,\n" +
                        "        \"DIDInfo\": null,\n" +
                        "        \"CorporationInfo\": {\n" +
                        "            \"Reqid\": 1154867993,\n" +
                        "            \"Corp_PayType\": \"OWN\",\n" +
                        "            \"CanAccountPay\": null,\n" +
                        "            \"CompanyType\": null,\n" +
                        "            \"CorpId\": null,\n" +
                        "            \"CorpName\": null,\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753857+0800)/\"\n" +
                        "        },\n" +
                        "        \"AppInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"ClientID\": \"\",\n" +
                        "            \"Latitude\": 0,\n" +
                        "            \"Longitude\": 0,\n" +
                        "            \"ClientVersion\": \"\",\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753860+0800)/\"\n" +
                        "        },\n" +
                        "        \"PaymentMainInfo\": {\n" +
                        "            \"ReqID\": 1154867993,\n" +
                        "            \"IsPrepaID\": \"\",\n" +
                        "            \"DataChange_LastTime\": \"/Date(1443274753863+0800)/\",\n" +
                        "            \"PayMethod\": \"\",\n" +
                        "            \"PayValidationMethod\": \"\",\n" +
                        "            \"BankValidationMethod\": \"\",\n" +
                        "            \"ValidationFailsReason\": \"\",\n" +
                        "            \"ClientOS\": \"\",\n" +
                        "            \"ClientIDOrIP\": \"\",\n" +
                        "            \"DeductType\": \"\"\n" +
                        "        },\n" +
                        "        \"UserProfile\": {\n" +
                        "            \"RECENT_IP\": null,\n" +
                        "            \"RECENT_PLATFORM\": null,\n" +
                        "            \"RECENT_DID\": null,\n" +
                        "            \"RECENT_CLIENTID\": null,\n" +
                        "            \"RECENT_BROWSER\": null,\n" +
                        "            \"MOB_L3M\": null,\n" +
                        "            \"MOST_TADDR\": null,\n" +
                        "            \"STATUS\": null,\n" +
                        "            \"CATEGORY\": null,\n" +
                        "            \"WALLET_MOB_BOUND\": null,\n" +
                        "            \"WALLET_EMAIL_BOUND\": null,\n" +
                        "            \"EMAIL_BOUND\": null,\n" +
                        "            \"MOB_BOUND\": null,\n" +
                        "            \"TRUST_IP_MOB\": null,\n" +
                        "            \"TRUST_CID_MOB\": null,\n" +
                        "            \"TRUST_DID_MOB\": null,\n" +
                        "            \"MOB_USED\": null,\n" +
                        "            \"CERT_ID\": null,\n" +
                        "            \"BIRTH\": null,\n" +
                        "            \"REGIST_IP\": null,\n" +
                        "            \"LASTLOGIN_IP\": null,\n" +
                        "            \"SIGNUPDATE\": \"/Date(-62135596800000+0800)/\",\n" +
                        "            \"LASTLOGIN_TIME\": \"/Date(-62135596800000+0800)/\",\n" +
                        "            \"GRADE\": 0,\n" +
                        "            \"POINT_BALANCE\": 0,\n" +
                        "            \"POINT_IN2Y\": 0,\n" +
                        "            \"MOB_CNT_L3M\": 0,\n" +
                        "            \"FRAUD_FLAG\": false\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"requestTime\": \"2015-9-26 21:39:14\"\n" +
                        "}",
                RiskEvent.class);
        System.out.println(getValueFromMap(fact.getEventBody(), "PaymentInfo.CardInfoList.CardInfoID"));
        System.out.println(Pattern.matches("(3)|(19)", "19"));
        System.out.println(Pattern.matches("(3)|(19)", "0"));
        System.out.println(Pattern.matches("(3)|(19)", "3"));
        System.out.println(Pattern.matches("(3)|(19)", "33"));
    }
}
