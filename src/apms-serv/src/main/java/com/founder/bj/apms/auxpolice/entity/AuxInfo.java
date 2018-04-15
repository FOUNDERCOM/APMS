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

package com.founder.bj.apms.auxpolice.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.sys.entity.SysDict;
import com.founder.bj.apms.sys.entity.SysUser;

/**
 * Description: Aux Information Entity.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_AUX_INFO")
@SuppressWarnings("unused")
public class AuxInfo implements Serializable {

    private static final long serialVersionUID = -6370270543226529188L;

    /** Id. */
    @Id
    @Column(name = "AUX_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** Name. */
    @Column(name = "AUX_NAME")
    private String name;

    /** Tel. */
    @Column(name = "AUX_TEL")
    private String tel;

    /** Mobile. */
    @Column(name = "AUX_MOBILE")
    private String mobile;

    /** Name. */
    @Column(name = "AUX_MAIL")
    private String mail;

    /** Identity Card. */
    @Column(name = "AUX_IDENTITY_CARD")
    private String identityCard;

    /** Old identity. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_OLD_IDENTITY", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'OLD_IDENTITY'")) })
    private SysDict oldIdentity;
    // CSON: LineLength

    /** Sex. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_SEX", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'SEX'")) })
    private SysDict sex;
    // CSON: LineLength

    /** Birthday. */
    @Column(name = "AUX_BIRTHDAY")
    private String birthday;

    /** Standing in police. */
    @Formula("(floor(months_between(SYSDATE, to_date(AUX_BIRTHDAY, 'yyyy-MM-dd')) / 12))")
    private Integer age;

    /** Nation. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_NATION", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'NATION'")) })
    private SysDict nation;
    // CSON: LineLength

    /** Health. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_HEALTH", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'HEALTH'")) })
    private SysDict health;
    // CSON: LineLength

    /** Political Status. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_POLITICAL_STATUS", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'POLITICAL_STATUS'")) })
    private SysDict politicalStatus;
    // CSON: LineLength

    /** Education Degree. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_EDUCATION_DEGREE", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'EDUCATION_DEGREE'")) })
    private SysDict eduDegree;
    // CSON: LineLength

    /** Institutions. */
    @Column(name = "AUX_INSTITUTIONS")
    private String institutions;

    /** Major. */
    @Column(name = "AUX_MAJOR")
    private String major;

    /** Native Place. */
    @Column(name = "AUX_NATIVE_PLACE")
    private String nativePlace;

    /** Job. */
    @Column(name = "AUX_JOB")
    private String job;

    /** Job Level. */
    @Column(name = "AUX_JOB_LEVEL")
    private Integer jobLevel;

    /** Join date. */
    @Column(name = "AUX_JOIN_DATE")
    private String joinDate;

    /** Standing in police. */
    @Formula("(floor(months_between(SYSDATE, to_date(AUX_JOIN_DATE, 'yyyy-MM-dd'))))")
    private Integer standing;

    /** Address province. */
    @Column(name = "AUX_ADD_PROVINCE")
    private String addProvince;
    /** Address city. */
    @Column(name = "AUX_ADD_CITY")
    private String addCity;
    /** Address country. */
    @Column(name = "AUX_ADD_COUNTRY")
    private String addCountry;
    /** Address detail. */
    @Column(name = "AUX_ADD_DETAIL")
    private String addDetail;
    /** Address postcode. */
    @Column(name = "AUX_ADD_POSTCODE")
    private String addPostcode;

    /** Base64 photo data. */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "AUX_PHOTO")
    private String photo;

    /** Status. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "AUX_STATUS", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'PROCESS_STATUS'")) })
    private SysDict status;
    // CSON: LineLength

    /** Salary 基本工资. */
    @Column(name = "AUX_SALARY_BASE")
    private Integer salaryBase;

    /** Salary 奖金. */
    @Column(name = "AUX_SALARY_BONUS")
    private Integer salaryBonus;

    /** Salary 所得税. */
    @Column(name = "AUX_SALARY_TAX")
    private Integer salaryTax;

    /** Salary 个付社保. */
    @Column(name = "AUX_SALARY_S_SS")
    private Integer salarySSS;

    /** Salary 个付公积金. */
    @Column(name = "AUX_SALARY_S_FUND")
    private Integer salarySFund;

    /** Salary 司付社保. */
    @Column(name = "AUX_SALARY_C_SS")
    private Integer salaryCSS;

    /** Salary 司付公积金. */
    @Column(name = "AUX_SALARY_C_FUND")
    private Integer salaryCFund;

    /** Salary 实发工资. */
    @Column(name = "AUX_SALARY_S_GET")
    private Integer salarySGet;

    /** Salary 司付工资. */
    @Column(name = "AUX_SALARY_C_PAY")
    private Integer salaryCPay;

    /** 是否是正常工资 .*/
    @Formula("(SELECT CASE WHEN (AUX_SALARY = B.BUREAU_STD_SALARY) THEN 1 ELSE 0 END FROM APMS_DEPT_BUREAU B, APMS_DEPT_STATION S WHERE B.BUREAU_ID = S.BUREAU_ID AND S.STATION_ID = STATION_ID)")
    private Boolean isSalaryNormal;

    /** Station which aux belongs to. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATION_ID")
    private DeptStation station;

    /** 最近更新人ID与用户表关联. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LATEST_UPDATE_USER")
    private SysUser lastUpdateUser;
    /** 最近更新时间. */
    @Column(name = "LATEST_UPDATE_DATE")
    private String lastUpdateDate;
    /** 最近更新IP. */
    @Column(name = "LATEST_UPDATE_IP")
    private String lastUpdateIp;

    /** 最近审核人ID与用户表关联. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LATEST_APPROVE_USER")
    private SysUser lastApproveUser;
    /** 最近审核时间. */
    @Column(name = "LATEST_APPROVE_DATE")
    private String lastApproveDate;
    /** 最近审核IP. */
    @Column(name = "LATEST_APPROVE_IP")
    private String lastApproveIp;

    /** 是否启用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    /** List of award. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxAward> awardList;

    /** List of education. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxEdu> eduList;

    /** List of family. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxFamily> familyList;

    /** List of punish. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxPunish> punishList;

    /** List of work. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxWork> workList;

    /** List of stuff files. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxStuffFile> fileList;

    /** List of appraise. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aux", cascade = {CascadeType.ALL})
    private Set<AuxAppraise> appraiseList;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name.
     *
     * @return return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the tel.
     *
     * @return return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Set tel.
     *
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Get the mobile.
     *
     * @return return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Set mobile.
     *
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Get the mail.
     *
     * @return return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set mail.
     *
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Get the identityCard.
     *
     * @return return the identityCard
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * Set identityCard.
     *
     * @param identityCard the identityCard to set
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * Get the oldIdentity.
     *
     * @return return the oldIdentity
     */
    public SysDict getOldIdentity() {
        return oldIdentity;
    }

    /**
     * Set oldIdentity.
     *
     * @param oldIdentity the oldIdentity to set
     */
    public void setOldIdentity(SysDict oldIdentity) {
        this.oldIdentity = oldIdentity;
    }

    /**
     * Get the sex.
     *
     * @return return the sex
     */
    public SysDict getSex() {
        return sex;
    }

    /**
     * Set sex.
     *
     * @param sex the sex to set
     */
    public void setSex(SysDict sex) {
        this.sex = sex;
    }

    /**
     * Get the birthday.
     *
     * @return return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Set birthday.
     *
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the age.
     *
     * @return return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Set age.
     *
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Get the nation.
     *
     * @return return the nation
     */
    public SysDict getNation() {
        return nation;
    }

    /**
     * Set nation.
     *
     * @param nation the nation to set
     */
    public void setNation(SysDict nation) {
        this.nation = nation;
    }

    /**
     * Get the health.
     *
     * @return return the health
     */
    public SysDict getHealth() {
        return health;
    }

    /**
     * Set health.
     *
     * @param health the health to set
     */
    public void setHealth(SysDict health) {
        this.health = health;
    }

    /**
     * Get the politicalStatus.
     *
     * @return return the politicalStatus
     */
    public SysDict getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * Set politicalStatus.
     *
     * @param politicalStatus the politicalStatus to set
     */
    public void setPoliticalStatus(SysDict politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * Get the eduDegree.
     *
     * @return return the eduDegree
     */
    public SysDict getEduDegree() {
        return eduDegree;
    }

    /**
     * Set eduDegree.
     *
     * @param eduDegree the eduDegree to set
     */
    public void setEduDegree(SysDict eduDegree) {
        this.eduDegree = eduDegree;
    }

    /**
     * Get the institutions.
     *
     * @return return the institutions
     */
    public String getInstitutions() {
        return institutions;
    }

    /**
     * Set institutions.
     *
     * @param institutions the institutions to set
     */
    public void setInstitutions(String institutions) {
        this.institutions = institutions;
    }

    /**
     * Get the major.
     *
     * @return return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Set major.
     *
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Get the nativePlace.
     *
     * @return return the nativePlace
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * Set nativePlace.
     *
     * @param nativePlace the nativePlace to set
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * Get the job.
     *
     * @return return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * Set job.
     *
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Get the jobLevel.
     *
     * @return return the jobLevel
     */
    public Integer getJobLevel() {
        return jobLevel;
    }

    /**
     * Set jobLevel.
     *
     * @param jobLevel the jobLevel to set
     */
    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    /**
     * Get the joinDate.
     *
     * @return return the joinDate
     */
    public String getJoinDate() {
        return joinDate;
    }

    /**
     * Set joinDate.
     *
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Get the standing.
     *
     * @return return the standing
     */
    public Integer getStanding() {
        return standing;
    }

    /**
     * Set standing.
     *
     * @param standing the standing to set
     */
    public void setStanding(Integer standing) {
        this.standing = standing;
    }

    /**
     * Get the addProvince.
     *
     * @return return the addProvince
     */
    public String getAddProvince() {
        return addProvince;
    }

    /**
     * Set addProvince.
     *
     * @param addProvince the addProvince to set
     */
    public void setAddProvince(String addProvince) {
        this.addProvince = addProvince;
    }

    /**
     * Get the addCity.
     *
     * @return return the addCity
     */
    public String getAddCity() {
        return addCity;
    }

    /**
     * Set addCity.
     *
     * @param addCity the addCity to set
     */
    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    /**
     * Get the addCountry.
     *
     * @return return the addCountry
     */
    public String getAddCountry() {
        return addCountry;
    }

    /**
     * Set addCountry.
     *
     * @param addCountry the addCountry to set
     */
    public void setAddCountry(String addCountry) {
        this.addCountry = addCountry;
    }

    /**
     * Get the addDetail.
     *
     * @return return the addDetail
     */
    public String getAddDetail() {
        return addDetail;
    }

    /**
     * Set addDetail.
     *
     * @param addDetail the addDetail to set
     */
    public void setAddDetail(String addDetail) {
        this.addDetail = addDetail;
    }

    /**
     * Get the addPostcode.
     *
     * @return return the addPostcode
     */
    public String getAddPostcode() {
        return addPostcode;
    }

    /**
     * Set addPostcode.
     *
     * @param addPostcode the addPostcode to set
     */
    public void setAddPostcode(String addPostcode) {
        this.addPostcode = addPostcode;
    }

    /**
     * Get the photo.
     *
     * @return return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Set photo.
     *
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Get the status.
     *
     * @return return the status
     */
    public SysDict getStatus() {
        return status;
    }

    /**
     * Set status.
     *
     * @param status the status to set
     */
    public void setStatus(SysDict status) {
        this.status = status;
    }

    /**
     * Get the salaryBase.
     *
     * @return return the salaryBase
     */
    public Integer getSalaryBase() {
        return salaryBase;
    }

    /**
     * Set salaryBase.
     *
     * @param salaryBase the salaryBase to set
     */
    public void setSalaryBase(Integer salaryBase) {
        this.salaryBase = salaryBase;
    }

    /**
     * Get the salaryBonus.
     *
     * @return return the salaryBonus
     */
    public Integer getSalaryBonus() {
        return salaryBonus;
    }

    /**
     * Set salaryBonus.
     *
     * @param salaryBonus the salaryBonus to set
     */
    public void setSalaryBonus(Integer salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    /**
     * Get the salaryTax.
     *
     * @return return the salaryTax
     */
    public Integer getSalaryTax() {
        return salaryTax;
    }

    /**
     * Set salaryTax.
     *
     * @param salaryTax the salaryTax to set
     */
    public void setSalaryTax(Integer salaryTax) {
        this.salaryTax = salaryTax;
    }

    /**
     * Get the salarySSS.
     *
     * @return return the salarySSS
     */
    public Integer getSalarySSS() {
        return salarySSS;
    }

    /**
     * Set salarySSS.
     *
     * @param salarySSS the salarySSS to set
     */
    public void setSalarySSS(Integer salarySSS) {
        this.salarySSS = salarySSS;
    }

    /**
     * Get the salarySFund.
     *
     * @return return the salarySFund
     */
    public Integer getSalarySFund() {
        return salarySFund;
    }

    /**
     * Set salarySFund.
     *
     * @param salarySFund the salarySFund to set
     */
    public void setSalarySFund(Integer salarySFund) {
        this.salarySFund = salarySFund;
    }

    /**
     * Get the salaryCSS.
     *
     * @return return the salaryCSS
     */
    public Integer getSalaryCSS() {
        return salaryCSS;
    }

    /**
     * Set salaryCSS.
     *
     * @param salaryCSS the salaryCSS to set
     */
    public void setSalaryCSS(Integer salaryCSS) {
        this.salaryCSS = salaryCSS;
    }

    /**
     * Get the salaryCFund.
     *
     * @return return the salaryCFund
     */
    public Integer getSalaryCFund() {
        return salaryCFund;
    }

    /**
     * Set salaryCFund.
     *
     * @param salaryCFund the salaryCFund to set
     */
    public void setSalaryCFund(Integer salaryCFund) {
        this.salaryCFund = salaryCFund;
    }

    /**
     * Get the salarySGet.
     *
     * @return return the salarySGet
     */
    public Integer getSalarySGet() {
        return salarySGet;
    }

    /**
     * Set salarySGet.
     *
     * @param salarySGet the salarySGet to set
     */
    public void setSalarySGet(Integer salarySGet) {
        this.salarySGet = salarySGet;
    }

    /**
     * Get the salaryCPay.
     *
     * @return return the salaryCPay
     */
    public Integer getSalaryCPay() {
        return salaryCPay;
    }

    /**
     * Set salaryCPay.
     *
     * @param salaryCPay the salaryCPay to set
     */
    public void setSalaryCPay(Integer salaryCPay) {
        this.salaryCPay = salaryCPay;
    }

    /**
     * Get the isSalaryNormal.
     *
     * @return return the isSalaryNormal
     */
    public Boolean getIsSalaryNormal() {
        return isSalaryNormal;
    }

    /**
     * Set isSalaryNormal.
     *
     * @param isSalaryNormal the isSalaryNormal to set
     */
    public void setIsSalaryNormal(Boolean isSalaryNormal) {
        this.isSalaryNormal = isSalaryNormal;
    }

    /**
     * Get the station.
     *
     * @return return the station
     */
    public DeptStation getStation() {
        return station;
    }

    /**
     * Set station.
     *
     * @param station the station to set
     */
    public void setStation(DeptStation station) {
        this.station = station;
    }

    /**
     * Get the lastUpdateUser.
     *
     * @return return the lastUpdateUser
     */
    public SysUser getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * Set lastUpdateUser.
     *
     * @param lastUpdateUser the lastUpdateUser to set
     */
    public void setLastUpdateUser(SysUser lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * Get the lastUpdateDate.
     *
     * @return return the lastUpdateDate
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Set lastUpdateDate.
     *
     * @param lastUpdateDate the lastUpdateDate to set
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Get the lastUpdateIp.
     *
     * @return return the lastUpdateIp
     */
    public String getLastUpdateIp() {
        return lastUpdateIp;
    }

    /**
     * Set lastUpdateIp.
     *
     * @param lastUpdateIp the lastUpdateIp to set
     */
    public void setLastUpdateIp(String lastUpdateIp) {
        this.lastUpdateIp = lastUpdateIp;
    }

    /**
     * Get the lastApproveUser.
     *
     * @return return the lastApproveUser
     */
    public SysUser getLastApproveUser() {
        return lastApproveUser;
    }

    /**
     * Set lastApproveUser.
     *
     * @param lastApproveUser the lastApproveUser to set
     */
    public void setLastApproveUser(SysUser lastApproveUser) {
        this.lastApproveUser = lastApproveUser;
    }

    /**
     * Get the lastApproveDate.
     *
     * @return return the lastApproveDate
     */
    public String getLastApproveDate() {
        return lastApproveDate;
    }

    /**
     * Set lastApproveDate.
     *
     * @param lastApproveDate the lastApproveDate to set
     */
    public void setLastApproveDate(String lastApproveDate) {
        this.lastApproveDate = lastApproveDate;
    }

    /**
     * Get the lastApproveIp.
     *
     * @return return the lastApproveIp
     */
    public String getLastApproveIp() {
        return lastApproveIp;
    }

    /**
     * Set lastApproveIp.
     *
     * @param lastApproveIp the lastApproveIp to set
     */
    public void setLastApproveIp(String lastApproveIp) {
        this.lastApproveIp = lastApproveIp;
    }

    /**
     * Get the isEnabled.
     *
     * @return return the isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * Set isEnabled.
     *
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Get the awardList.
     *
     * @return return the awardList
     */
    public Set<AuxAward> getAwardList() {
        return awardList;
    }

    /**
     * Set awardList.
     *
     * @param awardList the awardList to set
     */
    public void setAwardList(Set<AuxAward> awardList) {
        this.awardList = awardList;
    }

    /**
     * Get the eduList.
     *
     * @return return the eduList
     */
    public Set<AuxEdu> getEduList() {
        return eduList;
    }

    /**
     * Set eduList.
     *
     * @param eduList the eduList to set
     */
    public void setEduList(Set<AuxEdu> eduList) {
        this.eduList = eduList;
    }

    /**
     * Get the familyList.
     *
     * @return return the familyList
     */
    public Set<AuxFamily> getFamilyList() {
        return familyList;
    }

    /**
     * Set familyList.
     *
     * @param familyList the familyList to set
     */
    public void setFamilyList(Set<AuxFamily> familyList) {
        this.familyList = familyList;
    }

    /**
     * Get the punishList.
     *
     * @return return the punishList
     */
    public Set<AuxPunish> getPunishList() {
        return punishList;
    }

    /**
     * Set punishList.
     *
     * @param punishList the punishList to set
     */
    public void setPunishList(Set<AuxPunish> punishList) {
        this.punishList = punishList;
    }

    /**
     * Get the workList.
     *
     * @return return the workList
     */
    public Set<AuxWork> getWorkList() {
        return workList;
    }

    /**
     * Set workList.
     *
     * @param workList the workList to set
     */
    public void setWorkList(Set<AuxWork> workList) {
        this.workList = workList;
    }

    /**
     * Get the fileList.
     *
     * @return return the fileList
     */
    public Set<AuxStuffFile> getFileList() {
        return fileList;
    }

    /**
     * Set fileList.
     *
     * @param fileList the fileList to set
     */
    public void setFileList(Set<AuxStuffFile> fileList) {
        this.fileList = fileList;
    }

    /**
     * Get the appraiseList.
     *
     * @return return the appraiseList
     */
    public Set<AuxAppraise> getAppraiseList() {
        return appraiseList;
    }

    /**
     * Set appraiseList.
     *
     * @param appraiseList the appraiseList to set
     */
    public void setAppraiseList(Set<AuxAppraise> appraiseList) {
        this.appraiseList = appraiseList;
    }
}
