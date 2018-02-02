/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/2/1.
 */

public class OrderDetailModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"argsMap":{"name":"0#","province":"","city":"","create_ts":"20180131235243","countcuttingnum":22,"countbendnum":0,"countgroovingnum":0,"countgraphicalArea":3.08E7,"graphical_num":"22","total_job_area":"30.8","totalGraphicalArea":"17.6","batchGraphical_List":[{"batch_id":"BAT_4c4554b7b23e478982bee4424fbb1c3d","graphical_id":"4c4554b7b23e478982bee4424fbb1c3d"},{"batch_id":"BAT_ed2e42d2670d4281a831058ae093701c","graphical_id":"ed2e42d2670d4281a831058ae093701c"}],"orderno":"","material_type_num":2,"materialNum":10,"waste_area":1.2247766E7,"consumMaterialArea":4.3047766E7,"usearea_rate":0.715484283202989,"materialType":"316,316","byTypeList_2":["316/0.6/玫瑰金//1219.0*4000.0*5"]},"tempGraphicalSepcList":[{"item_id":"4f9ad6d81cfb4e0389c43ff4c3fffded","type":"316","color":"mgj","thickness_num":0.6,"orientation":"1","is_lines":"0","issize":"1","crafts":"","batch_id":"BAT_4c4554b7b23e478982bee4424fbb1c3d","typeChina":"316","colorChina":"玫瑰金","is_linesChina":"","orientationChina":"","issizeChina":"外尺","craftsChina":"","widthAndHeightList":["1600.0*1000.0*11","1200.0*1000.0*11"],"grapNameList":["1#","1#2"],"filepathList":["/group1/M00/00/3B/wKgBNlpx51eALQuVAAAOeRtxoU4336.png","/group1/M00/00/3B/wKgBNlpx52OAASOKAAAOeRtxoU4105.png"]}]}
     * otherData : null
     */

    private boolean success;
    private int code;
    private String msg;
    private DataBean data;
    private Object otherData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    public static class DataBean {
        /**
         * argsMap : {"name":"0#","province":"","city":"","create_ts":"20180131235243","countcuttingnum":22,"countbendnum":0,"countgroovingnum":0,"countgraphicalArea":3.08E7,"graphical_num":"22","total_job_area":"30.8","totalGraphicalArea":"17.6","batchGraphical_List":[{"batch_id":"BAT_4c4554b7b23e478982bee4424fbb1c3d","graphical_id":"4c4554b7b23e478982bee4424fbb1c3d"},{"batch_id":"BAT_ed2e42d2670d4281a831058ae093701c","graphical_id":"ed2e42d2670d4281a831058ae093701c"}],"orderno":"","material_type_num":2,"materialNum":10,"waste_area":1.2247766E7,"consumMaterialArea":4.3047766E7,"usearea_rate":0.715484283202989,"materialType":"316,316","byTypeList_2":["316/0.6/玫瑰金//1219.0*4000.0*5"]}
         * tempGraphicalSepcList : [{"item_id":"4f9ad6d81cfb4e0389c43ff4c3fffded","type":"316","color":"mgj","thickness_num":0.6,"orientation":"1","is_lines":"0","issize":"1","crafts":"","batch_id":"BAT_4c4554b7b23e478982bee4424fbb1c3d","typeChina":"316","colorChina":"玫瑰金","is_linesChina":"","orientationChina":"","issizeChina":"外尺","craftsChina":"","widthAndHeightList":["1600.0*1000.0*11","1200.0*1000.0*11"],"grapNameList":["1#","1#2"],"filepathList":["/group1/M00/00/3B/wKgBNlpx51eALQuVAAAOeRtxoU4336.png","/group1/M00/00/3B/wKgBNlpx52OAASOKAAAOeRtxoU4105.png"]}]
         */

        private ArgsMapBean argsMap;
        private List<TempGraphicalSepcListBean> tempGraphicalSepcList;

        public ArgsMapBean getArgsMap() {
            return argsMap;
        }

        public void setArgsMap(ArgsMapBean argsMap) {
            this.argsMap = argsMap;
        }

        public List<TempGraphicalSepcListBean> getTempGraphicalSepcList() {
            return tempGraphicalSepcList;
        }

        public void setTempGraphicalSepcList(List<TempGraphicalSepcListBean> tempGraphicalSepcList) {
            this.tempGraphicalSepcList = tempGraphicalSepcList;
        }

        public static class ArgsMapBean {
            /**
             * name : 0#
             * province :
             * city :
             * create_ts : 20180131235243
             * countcuttingnum : 22
             * countbendnum : 0
             * countgroovingnum : 0.0
             * countgraphicalArea : 3.08E7
             * graphical_num : 22
             * total_job_area : 30.8
             * totalGraphicalArea : 17.6
             * batchGraphical_List : [{"batch_id":"BAT_4c4554b7b23e478982bee4424fbb1c3d","graphical_id":"4c4554b7b23e478982bee4424fbb1c3d"},{"batch_id":"BAT_ed2e42d2670d4281a831058ae093701c","graphical_id":"ed2e42d2670d4281a831058ae093701c"}]
             * orderno :
             * material_type_num : 2
             * materialNum : 10
             * waste_area : 1.2247766E7
             * consumMaterialArea : 4.3047766E7
             * usearea_rate : 0.715484283202989
             * materialType : 316,316
             * byTypeList_2 : ["316/0.6/玫瑰金//1219.0*4000.0*5"]
             */

            private String name;
            private String province;
            private String city;
            private String create_ts;
            private int countcuttingnum;
            private int countbendnum;
            private double countgroovingnum;
            private double countgraphicalArea;
            private String graphical_num;
            private String total_job_area;
            private String totalGraphicalArea;
            private String orderno;
            private int material_type_num;
            private int materialNum;
            private double waste_area;
            private double consumMaterialArea;
            private double usearea_rate;
            private String materialType;
            private List<BatchGraphicalListBean> batchGraphical_List;
            private List<String> byTypeList_2;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCreate_ts() {
                return create_ts;
            }

            public void setCreate_ts(String create_ts) {
                this.create_ts = create_ts;
            }

            public int getCountcuttingnum() {
                return countcuttingnum;
            }

            public void setCountcuttingnum(int countcuttingnum) {
                this.countcuttingnum = countcuttingnum;
            }

            public int getCountbendnum() {
                return countbendnum;
            }

            public void setCountbendnum(int countbendnum) {
                this.countbendnum = countbendnum;
            }

            public double getCountgroovingnum() {
                return countgroovingnum;
            }

            public void setCountgroovingnum(double countgroovingnum) {
                this.countgroovingnum = countgroovingnum;
            }

            public double getCountgraphicalArea() {
                return countgraphicalArea;
            }

            public void setCountgraphicalArea(double countgraphicalArea) {
                this.countgraphicalArea = countgraphicalArea;
            }

            public String getGraphical_num() {
                return graphical_num;
            }

            public void setGraphical_num(String graphical_num) {
                this.graphical_num = graphical_num;
            }

            public String getTotal_job_area() {
                return total_job_area;
            }

            public void setTotal_job_area(String total_job_area) {
                this.total_job_area = total_job_area;
            }

            public String getTotalGraphicalArea() {
                return totalGraphicalArea;
            }

            public void setTotalGraphicalArea(String totalGraphicalArea) {
                this.totalGraphicalArea = totalGraphicalArea;
            }

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public int getMaterial_type_num() {
                return material_type_num;
            }

            public void setMaterial_type_num(int material_type_num) {
                this.material_type_num = material_type_num;
            }

            public int getMaterialNum() {
                return materialNum;
            }

            public void setMaterialNum(int materialNum) {
                this.materialNum = materialNum;
            }

            public double getWaste_area() {
                return waste_area;
            }

            public void setWaste_area(double waste_area) {
                this.waste_area = waste_area;
            }

            public double getConsumMaterialArea() {
                return consumMaterialArea;
            }

            public void setConsumMaterialArea(double consumMaterialArea) {
                this.consumMaterialArea = consumMaterialArea;
            }

            public double getUsearea_rate() {
                return usearea_rate;
            }

            public void setUsearea_rate(double usearea_rate) {
                this.usearea_rate = usearea_rate;
            }

            public String getMaterialType() {
                return materialType;
            }

            public void setMaterialType(String materialType) {
                this.materialType = materialType;
            }

            public List<BatchGraphicalListBean> getBatchGraphical_List() {
                return batchGraphical_List;
            }

            public void setBatchGraphical_List(List<BatchGraphicalListBean> batchGraphical_List) {
                this.batchGraphical_List = batchGraphical_List;
            }

            public List<String> getByTypeList_2() {
                return byTypeList_2;
            }

            public void setByTypeList_2(List<String> byTypeList_2) {
                this.byTypeList_2 = byTypeList_2;
            }

            public static class BatchGraphicalListBean {
                /**
                 * batch_id : BAT_4c4554b7b23e478982bee4424fbb1c3d
                 * graphical_id : 4c4554b7b23e478982bee4424fbb1c3d
                 */

                private String batch_id;
                private String graphical_id;

                public String getBatch_id() {
                    return batch_id;
                }

                public void setBatch_id(String batch_id) {
                    this.batch_id = batch_id;
                }

                public String getGraphical_id() {
                    return graphical_id;
                }

                public void setGraphical_id(String graphical_id) {
                    this.graphical_id = graphical_id;
                }
            }
        }

        public static class TempGraphicalSepcListBean {
            /**
             * item_id : 4f9ad6d81cfb4e0389c43ff4c3fffded
             * type : 316
             * color : mgj
             * thickness_num : 0.6
             * orientation : 1
             * is_lines : 0
             * issize : 1
             * crafts :
             * batch_id : BAT_4c4554b7b23e478982bee4424fbb1c3d
             * typeChina : 316
             * colorChina : 玫瑰金
             * is_linesChina :
             * orientationChina :
             * issizeChina : 外尺
             * craftsChina :
             * widthAndHeightList : ["1600.0*1000.0*11","1200.0*1000.0*11"]
             * grapNameList : ["1#","1#2"]
             * filepathList : ["/group1/M00/00/3B/wKgBNlpx51eALQuVAAAOeRtxoU4336.png","/group1/M00/00/3B/wKgBNlpx52OAASOKAAAOeRtxoU4105.png"]
             */

            private String item_id;
            private String type;
            private String color;
            private double thickness_num;
            private String orientation;
            private String is_lines;
            private String issize;
            private String crafts;
            private String batch_id;
            private String typeChina;
            private String colorChina;
            private String is_linesChina;
            private String orientationChina;
            private String issizeChina;
            private String craftsChina;
            private List<String> widthAndHeightList;
            private List<String> grapNameList;
            private List<String> filepathList;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public double getThickness_num() {
                return thickness_num;
            }

            public void setThickness_num(double thickness_num) {
                this.thickness_num = thickness_num;
            }

            public String getOrientation() {
                return orientation;
            }

            public void setOrientation(String orientation) {
                this.orientation = orientation;
            }

            public String getIs_lines() {
                return is_lines;
            }

            public void setIs_lines(String is_lines) {
                this.is_lines = is_lines;
            }

            public String getIssize() {
                return issize;
            }

            public void setIssize(String issize) {
                this.issize = issize;
            }

            public String getCrafts() {
                return crafts;
            }

            public void setCrafts(String crafts) {
                this.crafts = crafts;
            }

            public String getBatch_id() {
                return batch_id;
            }

            public void setBatch_id(String batch_id) {
                this.batch_id = batch_id;
            }

            public String getTypeChina() {
                return typeChina;
            }

            public void setTypeChina(String typeChina) {
                this.typeChina = typeChina;
            }

            public String getColorChina() {
                return colorChina;
            }

            public void setColorChina(String colorChina) {
                this.colorChina = colorChina;
            }

            public String getIs_linesChina() {
                return is_linesChina;
            }

            public void setIs_linesChina(String is_linesChina) {
                this.is_linesChina = is_linesChina;
            }

            public String getOrientationChina() {
                return orientationChina;
            }

            public void setOrientationChina(String orientationChina) {
                this.orientationChina = orientationChina;
            }

            public String getIssizeChina() {
                return issizeChina;
            }

            public void setIssizeChina(String issizeChina) {
                this.issizeChina = issizeChina;
            }

            public String getCraftsChina() {
                return craftsChina;
            }

            public void setCraftsChina(String craftsChina) {
                this.craftsChina = craftsChina;
            }

            public List<String> getWidthAndHeightList() {
                return widthAndHeightList;
            }

            public void setWidthAndHeightList(List<String> widthAndHeightList) {
                this.widthAndHeightList = widthAndHeightList;
            }

            public List<String> getGrapNameList() {
                return grapNameList;
            }

            public void setGrapNameList(List<String> grapNameList) {
                this.grapNameList = grapNameList;
            }

            public List<String> getFilepathList() {
                return filepathList;
            }

            public void setFilepathList(List<String> filepathList) {
                this.filepathList = filepathList;
            }
        }
    }
}
