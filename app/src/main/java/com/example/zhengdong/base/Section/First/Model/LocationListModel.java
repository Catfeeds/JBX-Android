/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Model;

/**
 * Created by zheng.dong on 2018/1/30.
 */

public class LocationListModel {

    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * regeocode : {"formatted_address":"福建省厦门市湖里区金山街道邦盾厦门办事处","addressComponent":{"country":"中国","province":"福建省","city":"厦门市","citycode":"0592","district":"湖里区","adcode":"350206","township":"金山街道","towncode":"350206005000"}}
     */

    private String status;
    private String info;
    private String infocode;
    private RegeocodeBean regeocode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public RegeocodeBean getRegeocode() {
        return regeocode;
    }

    public void setRegeocode(RegeocodeBean regeocode) {
        this.regeocode = regeocode;
    }

    public static class RegeocodeBean {
        /**
         * formatted_address : 福建省厦门市湖里区金山街道邦盾厦门办事处
         * addressComponent : {"country":"中国","province":"福建省","city":"厦门市","citycode":"0592","district":"湖里区","adcode":"350206","township":"金山街道","towncode":"350206005000"}
         */

        private String formatted_address;
        private AddressComponentBean addressComponent;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public AddressComponentBean getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentBean addressComponent) {
            this.addressComponent = addressComponent;
        }

        public static class AddressComponentBean {
            /**
             * country : 中国
             * province : 福建省
             * city : 厦门市
             * citycode : 0592
             * district : 湖里区
             * adcode : 350206
             * township : 金山街道
             * towncode : 350206005000
             */

            private String country;
            private String province;
            private String city;
            private String citycode;
            private String district;
            private String adcode;
            private String township;
            private String towncode;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getTownship() {
                return township;
            }

            public void setTownship(String township) {
                this.township = township;
            }

            public String getTowncode() {
                return towncode;
            }

            public void setTowncode(String towncode) {
                this.towncode = towncode;
            }
        }
    }
}
