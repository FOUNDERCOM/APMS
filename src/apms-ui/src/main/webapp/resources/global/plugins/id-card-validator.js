/* ***************************************************************************
 * EZ.JWAF/EZ.JCWAP: Easy series Production.
 * Including JWAF(Java-based Web Application Framework)
 * and JCWAP(Java-based Customized Web Application Platform).
 * Copyright (C) 2016-2017 the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of MIT License as published by
 * the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the MIT License for more details.
 *
 * You should have received a copy of the MIT License along
 * with this library; if not, write to the Free Software Foundation.
 * ***************************************************************************/

/**
 * Description: 身份证校验.<br>
 * Created by Jimmybly Lee on 2017/10/10.
 * @author Jimmybly Lee
 */
var IDCardValidator = function () {
    var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];    // 加权因子
    var ValidCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2];            // 身份证验证位值.10代表X

    var isValid = function (idCard) {
        idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格
        if (idCard.length === 15) {
            return isValidityBirthBy15IdCard(idCard);       //进行15位身份证的验证
        } else if (idCard.length === 18) {
            var a_idCard = idCard.split("");                // 得到身份证数组
            return !!(isValidityBirthBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard));
        } else {
            return false;
        }
    };

    /**
     * 判断身份证号码为18位时最后的验证位是否正确
     * @param a_idCard 身份证号码数组
     * @return
     */
    var isTrueValidateCodeBy18IdCard = function (a_idCard) {
        var sum = 0;                             // 声明加权求和变量
        if (a_idCard[17].toLowerCase() === 'x') {
            a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作
        }
        for (var i = 0; i < 17; i++) {
            sum += Wi[i] * a_idCard[i];            // 加权求和
        }
        var valCodePosition = sum % 11;                // 得到验证码所位置
        // noinspection EqualityComparisonWithCoercionJS
        return a_idCard[17] == ValidCode[valCodePosition];
    };

    /**
     * 验证18位数身份证号码中的生日是否是有效生日
     * @param idCard18 18位书身份证字符串
     * @return
     */
    var isValidityBirthBy18IdCard = function (idCard18) {
        var year = idCard18.substring(6, 10);
        var month = idCard18.substring(10, 12);
        var day = idCard18.substring(12, 14);
        var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
        // 这里用getFullYear()获取年份，避免千年虫问题
        return !(temp_date.getFullYear() !== parseFloat(year)
            || temp_date.getMonth() !== parseFloat(month) - 1
            || temp_date.getDate() !== parseFloat(day));
    };

    /**
     * 验证15位数身份证号码中的生日是否是有效生日
     * @param idCard15 15位书身份证字符串
     * @return
     */
    var isValidityBirthBy15IdCard = function (idCard15) {
        var year = idCard15.substring(6, 8);
        var month = idCard15.substring(8, 10);
        var day = idCard15.substring(10, 12);
        var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
        return !(temp_date.getYear() !== parseFloat(year)
            || temp_date.getMonth() !== parseFloat(month) - 1
            || temp_date.getDate() !== parseFloat(day));
    };

    var birthday = function(idCard) {
        idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格
        var year, month, day;
        if (idCard.length === 15) {
            year = idCard.substring(6, 8);
            month = idCard.substring(8, 10);
            day = idCard.substring(10, 12);
            return new Date(year, parseFloat(month) - 1, parseFloat(day));
        } else if (idCard.length === 18) {
            year = idCard.substring(6, 10);
            month = idCard.substring(10, 12);
            day = idCard.substring(12, 14);
            return new Date(year, parseFloat(month) - 1, parseFloat(day));
        } else {
            return "";
        }
    };

    /**
     * 通过身份证判断是男是女
     * @param idCard 15/18位身份证号码
     * @return string
     */
    var maleOrFemaleByIdCard = function (idCard) {
        idCard = trim(idCard.replace(/ /g, ""));        // 对身份证号码做处理。包括字符间有空格。
        if (idCard.length === 15) {
            if (idCard.substring(14, 15) % 2 === 0) {
                return 'female';
            } else {
                return 'male';
            }
        } else if (idCard.length === 18) {
            if (idCard.substring(14, 17) % 2 === 0) {
                return 'female';
            } else {
                return 'male';
            }
        } else {
            return "";
        }
    };

    //去掉字符串头尾空格
    function trim(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }

    return {
        validate: isValid,
        sex: maleOrFemaleByIdCard,
        birthday: birthday
    }
}();
