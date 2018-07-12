package com.appsoft.test;

public class TestStr {

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub
		// String str1 =\\"XY0, SGY1, XY_DM2, JYDZ3, SBFS_DM4, ISTRANSFER5,
		// ZCLX_DM6, GLDM7, ROWNUM_8, DJLB_DM9, CZSJ10, JYDZYZBM11, ZHZS_ID12,
		// LSGX13, CYRS14, _PK_15, FRZJJHM16, ZHZS_SBYF17, LXDH18, CWFZRDH19,
		// ZSFS_DM20, ZHZS_TSSJ21, SGY_DM22, ZCZB23, SYHJZD24, _ROWNUM_25,
		// CZY26, BSRY27, DATA_SOURCE28, SSCZ_DM29, CWFZR30, ZGKS_DM31,
		// CZY_DM32, NSRZT33, DADM34, ZJHMJMC35, ZCLX36, DJLB37, NSRSBH38,
		// PZSLJG_DM39, FRXM40, BSYDH41, NSRZT_DM42, ZSFS43, SSCZ44, SCJYQX45,
		// RKSJ46, DJHMC47, SBFS48, ZJGMC49, ZGKS50, DJSJ51, LSGX_DM52,
		// PZSLJG53\\";
		// String str2 =\\"房屋建筑业, 刘湘涛, 755, , 01, 03677389885, 1, 查帐征收, 马王堆办事处,
		// 长期, 2014-04-09, 长沙市芙蓉区西园投资咨询有限公司, 直接申报, , 芙蓉区地税局七分局, 2013-01-10, 5,
		// \\";
		// System.out.println(str1.split(\\",\\").length);
		// System.out.println(str2.split(\\",\\").length);
		// String filePath =
		// \\"C:\\code\\data_save\\ftp_in\\v_ac01_table#1516453176574_7986541.csv\\";
		// List<CSVRecord> read = CSVUtils.read(filePath);
		// long str = System.currentTimeMillis();
		// String reqc = UUID.randomUUID().toString();
		// long enda =System.currentTimeMillis();
		// System.out.println(enda-str);
		// FileUtils.copyFile(new File(\\"C:\\soft\\history.txt\\"), new
		// File(\\"C:\\soft\\history2.txt\\"));
		// System.out.println(\\"拷贝完成\\");
		// String path = \\"C:\\soft\\testcol\\nimi.csv\\";
		// Object[] obhead = {\\"clo1\\",\\"col2\\",\\"clo3\\"};
		// List<Object[]> li = new ArrayList<Object[]>();
		// Object[] ob2 = {\\"张三\\",\\"22\\",\\"155\\"};
		// Object[] ob3 = {\\"李四\\",\\"156\\"};
		// li.add(ob2);
		// li.add(ob3);
		// CSVUtils.writeCsv(obhead, li, path);
		// FileReader fr = new FileReader(new File(path));
		// Iterator<CSVRecord> readStream = CSVUtils.readStream(fr);
		// while(readStream.hasNext())
		// {
		// CSVRecord next = readStream.next();
		// for (Object ob : obhead)
		// {
		// System.out.println(next.get((String)ob));
		// }
		// }
		// String path = \\"C:\\soft\\testcol\\nimi2.csv\\";
		// Bean b = new Bean();
		// b.put(\\"clo1\\", \\"1\\");
		// b.put(\\"clo2\\", null);
		// b.put(\\"clo3\\", \\"\\");
		// Object[] array = b.values().toArray();
		// for (Object object : array) {
		// System.out.println(\\"--->\\"+object);
		// }
		// System.out.println(b.values().toArray().length);
		// Bean b2 = new Bean();
		// b2.put(\\"clo1\\", \\"1\\");
		// b2.put(\\"clo2\\", \\"\\");
		// b2.put(\\"clo3\\", null);
		// Object[] array = b.keySet().toArray();
		// System.out.println(array.length);
		// for (Object object : array) {
		// System.out.println(object);
		// }
		// Object[] array2 = b.values().toArray();
		// System.out.println(\\"-----------------------------------\\");
		// System.out.println(array2.length);
		// for (Object object : array2) {
		// System.out.println(object);
		// }
		// Object[] obhead = {\\"clo1\\",\\"clo2\\",\\"clo3\\"};
		// List li = new ArrayList<>();
		// li.add(b.values().toArray());
		// li.add(b2.values().toArray());
		// CSVUtils.writeCsv(b.keySet().toArray(), li, path);
		// System.out.println(\\"开始读---\\");
		//
		// FileReader fr = new FileReader(new File(path));
		// Iterator<CSVRecord> readStream = CSVUtils.readStream(fr);
		// while(readStream.hasNext())
		// {
		// CSVRecord next = readStream.next();
		// for (Object ob : obhead)
		// {
		// System.out.println(next.get((String)ob));
		// }
		// }
		// String path = \\"C:\\soft\\testcol\\nimi.csv\\";
		// Bean b = new Bean();
		// b.put(\\"col1\\", new Timestamp(System.currentTimeMillis()));
		// Object[] obj = b.values().toArray();
		// List li = new ArrayList<>();
		// li.add(obj);
		// CSVUtils.writeCsv(b.keySet().toArray(), li, path);
		// System.out.println(\\"-------\\");
		// FileReader fr = new FileReader(new File(path));
		// Iterator<CSVRecord> readStream = CSVUtils.readStream(fr);
		// while(readStream.hasNext()){
		// CSVRecord next = readStream.next();
		// System.out.println(next);
		// }
		// long l = 6001/5000;
		// System.out.println(l);
		// String str1 =
		// \\"ID,ALARMMODE,ALARMTYPE,ALARMTIME,ALARMPHONE,ALARMNAME,SEX,CASEAREA,CASEPCS,ROAD,ROUND,CASEADDR,CASETIME,SELEPHONE,CASETYPE,CASESUBTYPE,INSTRUMENTALITY,GADEGREE,YJDEGREE,OPERATORID,OPERATORNAME,OPERATORDEPTID,CASEDESCRIBE,CASEINFO,FINE,FIRENAME,FIRELEVEL,FIRETYPE,FIREOBJECT,BUILDTYPE,FIREPOSITION,BUILDFLOOR,MONITOR,ALARMID,USERNAME,USERADDR,VOXID,WOUNDNUM,DEATHNUM,CCSSNUM,MANAGERUNIT,TAXIFLAG,TAXICARNUM,TAXICARUNIT,INSERTTIME,ZBRQ,ZBLX,CELLLLTUDE,ADDRLLTUDE,NOTIFYZZBS,GBALARMNO,RKSJ\\";
		// String str2 =
		// \\"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?\\";
		// System.out.println(\\"str1-->\\"+str1.split(\\",\\").length);
		// System.out.println(\\"str1-->\\"+str2.split(\\",\\").length);
		// Map<String, Object> mm = new LinkedHashMap<String, Object>();
		// mm.put(\\"id\\", 01);
		// mm.put(\\"name\\", \\"hg\\");
		// Object[] array = mm.keySet().toArray();
		// long str = System.currentTimeMillis();
		// System.out.println(StringUtils.join(array, \\",\\"));
		// System.out.println(System.currentTimeMillis()-str);
		// String str1 = \\"2017-11-09\\";
		// String str2 = \\"2017-11-09 08:11:12\\";
		// System.out.println(str1.length());
		// System.out.println(str2.length());
		// String path = \\"C:\\soft\\testcol\\nimi3.csv\\";
		// FileReader fr = new FileReader(path);
		// Iterator<CSVRecord> readStream = CSVUtils.readStream(fr);
		// while(readStream.hasNext()){
		// CSVRecord next = readStream.next();
		// boolean consistent = next.isConsistent();
		// System.out.println(\\"consistent-->\\"+consistent);
		// }
		//// while(readStream.hasNext()){
		// CSVRecord next = readStream.next();
		// System.out.println(\\"--->\\"+next.get(\\"clo3\\"));
		// Map<String, String> map = next.toMap();
		// Object[] array = map.keySet().toArray();
		// for (Object object : array) {
		// System.out.println(object);
		// }
		//// Iterator<Entry<String, String>> it = map.entrySet().iterator();
		//// System.out.println(\\"-------------------\\");
		//// while(it.hasNext())
		//// {
		//// Entry<String, String> next2 = it.next();
		//// System.out.println(\\"key-->\\"+next2.getKey()+\\"----->value-->\\"+next2.getValue());
		//// }
		// break;
		// }
		// String path = \\"C:\\soft\\testcol\\nimi3.csv\\";
		// Bean b1 = new Bean();
		// b1.put(\\"col1\\", \\"aa\\");
		// b1.put(\\"col2\\", \\"99,00\n,\\");
		// b1.put(\\"col3\\", \\"ms\\");
		// b1.put(\\"col4\\", \\"\n\\");
		//
		// Bean b2 = new Bean();
		// b2.put(\\"col1\\", \\"aa\\");
		// b2.put(\\"col2\\", \\"1100\n,\\");
		// b2.put(\\"col3\\", \\"ms\\");
		//// b2.put(\\"col3\\", \\"\n\\");
		// List li = new ArrayList<>();
		// li.add(b1.values().toArray());
		// li.add(b2.values().toArray());
		// CSVUtils.writeCsv(b1.keySet().toArray(), li, path);
		// System.out.println(\\"文件写入完成\\");
		// FileReader fr = new FileReader(path);
		// Iterator<CSVRecord> readStream = CSVUtils.readStream(fr);
		// while(readStream.hasNext()){
		// CSVRecord next = readStream.next();
		// System.out.println(\\"col2-->\\"+next.get(\\"col2\\")+\\"--col2长度--\\"+next.get(\\"col2\\").length());
		// System.out.println(\\"col3-->\\"+next.get(\\"col3\\")+\\"--col3长度--\\"+next.get(\\"col3\\").length());
		// System.out.println(\\"------------\\");
		// }
		// String str = \\"2014-06-13 17:23:29:000\\";
		// System.out.println(str.substring(0, 19));
		// String path =
		// \\"C:\\soft\\testcol\\errorfile\\BD_ALARMRESULT_1#1516788017633_9742376.csv\\";
		// FileInputStream fi = new FileInputStream(path);
		// byte[] by = new byte[1024*10];
		// int read = 0;
		// FileOutputStream ou = new FileOutputStream(new
		// File(\\"C:\\soft\\testcol\\errorfile\\out.csv\\"),true);
		// String str = null;
		// while((read=fi.read(by))!=-1){
		// str = new String(by);
		// ou.write(by);
		// }
		// fi.close();
		// ou.flush();
		// ou.close();
		// int n = 4;
		// int m = 60;
		// int si = 5;
		// int page = (m-n)/si;
		// System.out.println(\\"页数\\"+page);
		// for (int i = 0; i <= page; i++) {
		// System.out.println(\\"第\\"+i+\\"次取,从\\"+(i*si+n)+\\"条取\\"+si+\\"条数据\\");
		// }
		// String str = \\"a_bd, a_hcbd, bd_alarminfo_perman_1,
		// bd_alarmresult_1, m_cba_baseinfo, m_cba_case, rdrk_s,
		// scene_analysis_result, table_name, talbe_number, tb_zwbz_dcbzjg,
		// td_sm_organization, td_sm_orguser, td_sm_role, td_sm_user,
		// td_sm_userrole, temp, temp_jjbd, temp_sjhm, temp_sjhm_all,
		// temp_sjhm_bz, t_af_jdcxx, t_af_jwhxx, t_af_jwqsxx, t_af_lmpcxx,
		// t_af_sqfaqkdjxx, t_af_sqgbxx, t_af_sqjbxx, t_af_sqjfwfsszpxx,
		// t_af_sqmjxx, t_af_xgdxx, t_af_xgdyxx, t_af_xldxx, t_af_xldyxx,
		// t_af_xqjbxx, t_af_xqjfwfssxx, t_af_zakkxlxx, t_af_zakkxx,
		// t_af_zakywpxx, t_af_zapcxx, t_af_zbhcyxx, t_af_zbhjbxx, t_ajjbxxb,
		// t_ajjbxxb_trigger_cdc, t_bs_rk_ztry_jx2, t_common_dictionary,
		// t_common_dictionary系统字典表, t_common_dzjlx, t_common_dzjlx街路巷代码表,
		// t_common_dzxx, t_common_dzxx地址信息, t_common_hnxzqh,
		// t_common_organization_sjjs, t_common_pcsjwq_jbxx, t_common_stxx,
		// t_common_stxx实体建筑物信息, t_common_user_jbxx, t_cyry_s, t_gzgl_pcsxchd,
		// t_jjcjxx, t_jjcjxx_trigger_cdc, t_lbadwxxbs, t_lbaryxxbs,
		// t_ldrk_jzzxx, t_ldrk_simple, t_linshi_ztry_1105,
		// t_lsgl_czfw_czrxx_del, t_lsgl_czfw_czrxx承租人信息, t_lsgl_czfw_czxx,
		// t_lsgl_czfw_jbxx, t_lsgl_dwfw_gl, t_lsgl_dwfw_gl单位房屋关联表,
		// t_lsgl_fw_czjl, t_lsgl_fw_czjl房屋出租记录, t_lsgl_fw_czwxcdj,
		// t_lsgl_fw_jbxx, t_lsgl_fw_jbxx房屋基本信息, t_lsgl_fw_zpxx,
		// t_lsgl_fw_zpxx房屋照片信息, t_lsgl_ldrk_jzzxx, t_lsgl_ldrk_jzzxx居住证信息,
		// t_lsgl_ldrk_yqzx, t_lsgl_ldrk_zkyq, t_lsgl_ldrk_zkyq流动人口延期,
		// t_lsgl_rkfw_gl, t_lsgl_rkfw_gl人口房屋关联表, t_lsgl_rk_czrk,
		// t_lsgl_rk_czrk常住人口信息表, t_lsgl_rk_gxr, t_lsgl_rk_gxr人口关系人表,
		// t_lsgl_rk_jbxx, t_lsgl_rk_jbxx人口基本信息, t_lsgl_rk_jhsy,
		// t_lsgl_rk_jhsy计划生育, t_lsgl_rk_jwrkxx, t_lsgl_rk_jwrkxx境外人口信息,
		// t_lsgl_rk_jzrkxx, t_lsgl_rk_jzrkxx寄住人口信息, t_lsgl_rk_ldrkzzxx,
		// t_lsgl_rk_rylb, t_lsgl_rk_rylb人员类别, t_lsgl_rk_tmtz,
		// t_lsgl_rk_tmtz人员体貌特征, t_lsgl_rk_wlhczhk, t_lsgl_rk_zpxx,
		// t_lsgl_rk_zpxx人口照片信息, t_lsgl_ry_lxfs, t_lsgl_ry_lxfs人员联系方式,
		// t_lsgl_ry_xsbx, t_lsgl_ry_xsbx人员现实表现, t_percase_matched,
		// t_sawp_qd_wpqd, t_sawp_qd_ybwp, t_sawp_wp_ybwp,
		// t_sawp_wp_ybwp_trigger_cdc, t_swjcjlscjds, t_swjcjsjz_jds,
		// t_swjlscjds, t_swjsjz_jds, t_swklsjhzzjjds, t_swqscjjds,
		// t_swqxjlzg_jds, t_swsdtlqxjds, t_table_db, t_tab_yqxx, t_tales_tj,
		// t_wfrclqkb, t_wfrclqkb_trigger_cdc, t_wfrjbxxb,
		// t_wfrjbxxb_trigger_cdc, t_wfrwfxxb, t_wfrztxxb, t_xczatjxys,
		// t_xsbpzdbjds, t_xsbylatzs, t_xschtzs, t_xscxajjds, t_xsdbtzs,
		// t_xsdbzrfkjds, t_xshyz, t_xsjcjsjzjds, t_xsjcqbhsjds, t_xsjcz,
		// t_xsjltzs, t_xsjsjzjds, t_xslajds, t_xspabgs, t_xspagzs, t_xsqbhsjds,
		// t_xssftzs, t_xsshrxxb, t_xswdbbyjds, t_xsysqsyjs, t_xszaglfx,
		// t_xzbycfjds, t_xzcfjds, t_xzchz, t_xzcxxzcfjds, t_xzjcqzgljdzms,
		// t_xzjcsrjyzms, t_xzjljstzs, t_xzqzgljdjds, t_xzsadjb, t_xzsrjyjds,
		// t_xztqjcqzgljdjds, t_xztqjcsrjyjds, t_xzzatjxys, t_xzzlsqjdjds,
		// t_xzzltztzs, t_za_ddccj, t_za_ddccj电动车, t_za_dwhc, t_za_dwhc单位核查,
		// t_za_dw_abss, t_za_dw_abss安保设施, t_za_dw_aqjcxx,
		// t_za_dw_aqjcxx单位安全检查信息, t_za_dw_aqsbxx, t_za_dw_aqsbxx安全设备信息,
		// t_za_dw_aqyh, t_za_dw_aqyh安全隐患, t_za_dw_badys, t_za_dw_badys单位不安定因素,
		// t_za_dw_bwjg, t_za_dw_bwjg保卫机构, t_za_dw_bwjys, t_za_dw_bwjys保卫建议书,
		// t_za_dw_bwzd, t_za_dw_bwzd保卫制度, t_za_dw_dwjcxx, t_za_dw_dwjcxx单位奖惩信息,
		// t_za_dw_ggcs, t_za_dw_ggcs公共场所表, t_za_dw_jcpz, t_za_dw_jcpz_jjrjc,
		// t_za_dw_jcpz_jjrjc配置-节假日, t_za_dw_jcpz单位检查配置, t_za_dw_jfdw,
		// t_za_dw_jfdw_nsxx, t_za_dw_jfdw技防单位, t_za_dw_jxcs, t_za_dw_jxcs九小场所,
		// t_za_dw_nbdw, t_za_dw_swdw, t_za_dw_swdw涉外单位, t_za_dw_tfsjya,
		// t_za_dw_tfsjya突发事件预案, t_za_dw_thksy, t_za_dw_thksy特行开锁业,
		// t_za_dw_thlgy, t_za_dw_thlgy特行旅馆业, t_za_dw_thqtlxx,
		// t_za_dw_thqtlxx特行其他类信息, t_za_dw_tzhy, t_za_dw_tzhy特种行业,
		// t_za_dw_wxwpdw, t_za_dw_wxwpdw危险物品单位, t_za_dw_yayl,
		// t_za_dw_yayl单位预案演练, t_za_dw_zdyhbw, t_za_dw_zdyhbw单位重点要害部位,
		// t_za_dw_zhsg, t_za_dw_zhsg灾害事故, t_za_dxhdxx, t_za_dxhdxx治安大型活动,
		// t_za_jg_dwpf, t_za_jg_jbxx, t_za_jg_jbxx机构基本信息, t_za_jg_swfwcs,
		// t_za_jg_swfwcs上网服务场所, t_za_jg_zpxx, t_za_jg_zpxx单位照片, t_za_nb_dwjc,
		// t_za_nb_dwjc_zp, t_za_nb_dwjc内保单位检查, t_za_rhzf, t_za_ry_abryxx,
		// t_za_ry_abryxx安保人员信息, t_za_ry_cyry, t_za_ry_cyrypfxx,
		// t_za_ry_cyrypfxx从业人员派发, t_za_ry_cyry单位从业人员信息, t_za_wlsqxx,
		// t_za_wlsqxx社区网络信息, t_za_wlsq_lt, t_za_wlsq_lt网络社区论坛信息, t_za_wlsq_qqq,
		// t_za_wlsq_qqq网络社区qq群, t_za_wlsq_wz, t_za_wlsq_wz网络社区网站, t_za_wyxq,
		// t_za_wyxq_aqjc, t_za_wyxq_aqjc_zp, t_za_wyxq治安物业小区,
		// t_za_yhbw_zpxx_del, t_zddmxxb, t_zddmxxb_trigger_cdc, t_zdry_ckyjxx,
		// t_zdry_fkxx, t_zdry_fkxx重点人员反馈信息, t_zdry_gzdx,
		// t_zdry_gzdx_gzryxx_del, t_zdry_gzdx_jsbrzsxx_del,
		// t_zdry_gzdx_jsbr_del, t_zdry_gzdx_jzdxxx_del, t_zdry_gzdx_swry_del,
		// t_zdry_gzdx_swsfjl_del, t_zdry_gzdx_xdryxx_del, t_zdry_gzdx重点人员工作对象,
		// t_zdry_qgqlzdry, t_zdry_rypf, t_zdry_rypf重点人员派发, t_zdry_ryth,
		// t_zdry_ryth重点人员退回信息, t_zdry_sjth, t_zdry_sjth市局重点人员退回, t_zdry_wffzjl,
		// t_zdry_wtxx, t_zdry_wtxx重点人员托管表, t_zdry_xdchnjxx, t_zdry_xsbx,
		// t_zdry_xsbx重点对象现实表现, t_zdry_yjqsxx, t_zdry_zfbafk, t_zfqy_ryjbxxb,
		// t_zfqy_ryjbxxb_trigger_cdc, t_zyqb_ggxx, t_zyqb_sjxx, use_tab, xtglb,
		// ykt_att_kqdata, ykt_com_empcard, ykt_dr_k1dev, ykt_fp_dev,
		// ykt_hrms_dpt, ykt_hrms_emp, ykt_ndr2_cardevent, ykt_ndr2_dev,
		// ykt_ndr2_door, ykt_pos_xfdata, 机动车信息t_af_jdcxx, 警务区/室信息t_af_jwqsxx,
		// 居委会信息t_af_jwhxx, 可疑物品信息t_af_zakywpxx, 路面盘查信息登记t_af_lmpcxx,
		// 社区干部信息t_af_sqgbxx, 社区基本信息t_af_sqjbxx, 社区民警信息t_af_sqmjxx,
		// 小区基本信息t_af_xqjbxx, 协管队信息t_af_xgdxx, 协管队员信息t_af_xgdyxx,
		// 巡逻队信息t_af_xldxx, 巡逻队员信息t_af_xldyxx, 治安卡口信息t_af_zakkxx,
		// 治安卡口巡逻信息t_af_zakkxlxx, 治安盘查信息t_af_zapcxx, 治保会成员信息t_af_zbhcyxx,
		// 治保会信息t_af_zbhjbxx\\";
		// String[] split = str.split(\\",\\");
		// for (int i = 0; i < split.length; i++) {
		// System.out.println(\\"truncate table \\"+split[i]+\\";\\");
		// }
		// String str = "{\"OPERATORNAME\":\"袁尧\",\"CASETIME\":\"2017-03-16
		// 224607000\",\"ROAD\":\"\",\"INSERTTIME\":\"2017-03-16
		// 224715000\",\"ROWNUM_\":7510033,\"CASETYPE\":\"020000\",\"OPERATORID\":\"942906\",\"OPERATORDEPTID\":\"430121290000\",\"DEATHNUM\":\"0\",\"USERNAME\":\"不详\",\"FINE\":\"10\",\"WOUNDNUM\":\"0\",\"ID\":\"2017031622460710942906\",\"ADDRLLTUDE\":\"\",\"ZBLX\":0,\"TAXICARUNIT\":\"\",\"GBALARMNO\":\"\",\"FIREPOSITION\":\"\",\"MANAGERUNIT\":\"430121420000\",\"_PK_\":\"2017031622460710942906\",\"ALARMMODE\":\"01\",\"CASEINFO\":\"报警称上述地其电脑被盗\",\"BUILDTYPE\":\"\",\"TAXIFLAG\":\"0\",\"FIREOBJECT\":\"\",\"_ROWNUM_\":32,\"CELLLLTUDE\":\"\",\"ALARMNAME\":\"廖\",\"ROUND\":\"\",\"MONITOR\":\"\",\"TAXICARNUM\":\"\",\"SEX\":\"男\",\"CASEAREA\":\"430121000000\",\"CASEPCS\":\"430121420000\",\"ZBRQ\":\"201703162\",\"YJDEGREE\":\"\",\"CASEADDR\":\"安沙信息学校男生宿舍50栋112号\",\"ALARMID\":\"\",\"FIRENAME\":\"\",\"NOTIFYZZBS\":0,\"FIRELEVEL\":\"\",\"CASEDESCRIBE\":\"2017-03-16
		// 224607廖男联系电话15116036057报警称在安沙信息学校男生宿舍50栋112号发生治安警情-盗窃案件详情信息报警称上述地其电脑被盗\",\"SELEPHONE\":\"15116036057\",\"BUILDFLOOR\":\"\",\"CCSSNUM\":\"0\",\"FIRETYPE\":\"\",\"ALARMTYPE\":\"01\",\"ALARMPHONE\":\"15116036057\",\"INSTRUMENTALITY\":\"\",\"USERADDR\":\"不详\",\"CASESUBTYPE\":\"020200\",\"RKSJ\":\"2017-03-16
		// 224341000\",\"GADEGREE\":\"03\",\"VOXID\":\"20170316225011_110_15116036057\",\"ALAR,\"CASEPCS\":\"\",\"ZBRQ\":\"201407223\",\"YJDEGREE\":\"\",\"CASEADDR\":\"\",\"ALARMID\":\"\",\"FIRENAME\":\"\",\"NOTIFYZZBS\":0,\"FIRELEVEL\":\"\",\"CASEDESCRIBE\":\"2014-07-22
		// 175428群众男联系电话13975196124报警称在发生非警情类-其他非警情案件详情信息\",\"SELEPHONE\":\"13975196124\",\"BUILDFLOOR\":\"\",\"CCSSNUM\":\"0\",\"FIRETYPE\":\"\",\"ALARMTYPE\":\"01\",\"ALARMPHONE\":\"13975196124\",\"INSTRUMENTALITY\":\"\",\"USERADDR\":\"不详\",\"CASESUBTYPE\":\"905000\",\"RKSJ\":\"2017-01-11
		// 161927000\",\"GADEGREE\":\"99\",\"VOXID\":\"20140722175214_110_13975196124\",\"ALARMTIME\":\"2014-07-22
		// 175428000\"}";
		// JSONObject.parseObject(str, new Bean().getClass());
		// String str = "1985-11-24 00:00:00:000";
		// System.out.println(str.substring(0, 19));
		// String str = "";
		// System.out.println(str.length());
		// Bean b = new Bean();
		// b.put("clo1", "栋欧菲斯你");
		// b.put("clo2", "123九分裤数据开放");
		// ByteArrayOutputStream by = new ByteArrayOutputStream();
		// ObjectOutputStream ou = new ObjectOutputStream(by);
		// ou.writeObject(b);
		// byte[] byteArray = by.toByteArray();
		// ou.close();
		// FileOutputStream fi = new
		// FileOutputStream("C:\\soft\\testcol\\errorfile\\123.csv");
		// fi.write(byteArray);
		// fi.flush();
		// fi.close();
		// FileInputStream fin = new
		// FileInputStream("C:\\soft\\testcol\\errorfile\\123.csv");
		// ObjectInputStream objin = new ObjectInputStream(fin);
		// Bean b2 = new Bean();
		// b2 = (Bean) objin.readObject();
		// objin.close();
		// System.out.println(b2);
		// String str = "sffssdf|77777";
		// String[] split = str.split("\\|");
		// System.out.println(split[0]);
		// System.out.println(split[1]);
		// String str = "select * from #table_code# where djsj > '{select
		// max(djsj) from #table_code#}'";
		// String[] split = str.split("\\|");
		// for (int i = 0; i < split.length; i++) {
		// System.out.println(split[i]);
		// }
		// String str1 =split[1];
		// System.out.println(str1.substring(str1.indexOf("{")+1,
		// str1.indexOf("}")));
		// String str = "select * from T_ZA_RY_CYRY where rksj >
		// to_date('2018/1/29 18:08:15','yyyy-MM-dd HH24:mi:ss')";
		// String encode = Base64.encodeBase64String(str.getBytes());
		// System.out.println(encode);
		// System.out.println(encode.length());
		// String filepath =
		// "C:\\Users\\Jack\\Desktop\\c2VsZWN0ICogZnJvbSBUX1pBX1JZX0NZUlkgd2hlcmUgcmtzaiA+IHRvX2RhdGUoJzIwMTgvMS8yOSAxODowODoxNScsJ3l5eXktTU0tZGQgSEgyNDptaTpzcycp.csv";
		// File file = new File(filepath);
		// String name = file.getName().replace(".csv", "");
		// byte[] decodeBase64 = Base64.decodeBase64(name);
		// String al = new String(decodeBase64);
		// System.out.println(al);
		// long l = 1396972800000l;
		// Date date = new java.sql.Date(l);
		// System.out.println(date);
		// System.out.println(250/5000);
		// String path = "C:\\soft\\testcol\\errorfile\\idd.txt";
		// BufferedReader bufferedReader = new BufferedReader(new
		// InputStreamReader(new FileInputStream(path)));
		// StringBuffer sb = new StringBuffer();
		// String re = null;
		// while((re = bufferedReader.readLine())!=null){
		// sb.append("'").append(re).append("',");
		// }
		// bufferedReader.close();
		// re = sb.toString();
		// System.out.println(re.substring(0, re.length()-1));
		// long l = 1517155200000l;
		// Date date = new Date(l);
		// System.out.println(date);

		// StringBuffer sb = new StringBuffer();
		// sb.append("'V_AC01_TABLE',").append("'V_IN_ZHZSDSJ_FDCCFJQTJSGCXMXX',").append("'V_IN_ZHZSDSYSDJXX',")
		// .append("'V_IN_ZHZSGONGSHANG_DJXX',")
		// .append("'V_IN_ZHZSGS_GSDJXX',").append("'V_IN_ZHZSSWJ_JYZXX',")
		// .append("'V_KC21_TABLE',").append("'V_XARQ',").append("'V_VEHICLE'");
		// System.out.println(sb.toString());
		// String str = "2018-02-04 04:33:35:000";
		// System.out.println(str.length());
		// BloomFilter<String> bf = new BloomFilter<String>(null, 0, null,
		// null);
		String sfzh = "430103199702190523";
		String substring = sfzh.substring(0, 4);
		String substring2 = sfzh.substring(6, 10);
		String substring3 = sfzh.substring(10, 12);
		System.out.println("省份" + substring);
		System.out.println("年份" + substring2);
		System.out.println("月份" + substring3);
	}
}
