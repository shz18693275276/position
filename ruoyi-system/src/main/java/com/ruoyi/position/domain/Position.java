package com.ruoyi.position.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人力资源对象 position
 *
 * @author anan
 * @date 2021-03-01
 */
public class Position extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 一级机构
     */
    @Excel(name = "一级机构")
    private String oneInstitutions;

    /**
     * 二级机构
     */
    @Excel(name = "二级机构")
    private String twoInstitutions;

    /**
     * 三级机构
     */
    @Excel(name = "三级机构")
    private String threeInstitutions;

    /**
     * 职位分类
     */
    @Excel(name = "职位分类")
    private String positionType;

    /**
     * 职位名称
     */
    @Excel(name = "职位名称")
    private String positionName;

    /**
     * 职称
     */
    @Excel(name = "职称")
    private String position;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String phone;

    /**
     * qq
     */
    @Excel(name = "qq")
    private String qq;

    /**
     * 手机
     */
    @Excel(name = "手机")
    private String mobilePhone;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 邮编
     */
    @Excel(name = "邮编")
    private String postcode;

    /**
     * 国籍
     */
    @Excel(name = "国籍")
    private String nationality;

    /**
     * 出生地
     */
    @Excel(name = "出生地")
    private String birthpalce;

    /**
     * 生日
     */
    @Excel(name = "生日")
    private String birthday;

    /**
     * 名族
     */
    @Excel(name = "名族")
    private String ethnic;

    /**
     * 宗教信仰
     */
    @Excel(name = "宗教信仰")
    private String religious;

    /**
     * 政治面貌
     */
    @Excel(name = "政治面貌")
    private String politicesStatus;

    /**
     * 身份证
     */
    @Excel(name = "身份证")
    private String idCard;

    /**
     * 社会保障号
     */
    @Excel(name = "社会保障号")
    private String socialSecurityNumber;

    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private String age;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private String educationBackground;

    /**
     * 教育年限
     */
    @Excel(name = "教育年限")
    private String averageEducationYears;

    /**
     * 学历专业
     */
    @Excel(name = "学历专业")
    private String educationMajor;

    /**
     * 薪酬标准
     */
    @Excel(name = "薪酬标准")
    private String salaryStandard;

    /**
     * 开户行
     */
    @Excel(name = "开户行")
    private String openingBank;

    /**
     * 账号
     */
    @Excel(name = "账号")
    private String account;

    /**
     * 登记人
     */
    @Excel(name = "登记人")
    private String registrant;

    /**
     * 特长
     */
    @Excel(name = "特长")
    private String specialty;

    /**
     * 爱好
     */
    @Excel(name = "爱好")
    private String hobby;

    /**
     * 个人简介
     */
    @Excel(name = "个人简介")
    private String individualResume;

    /**
     * 家庭关系
     */
    @Excel(name = "家庭关系")
    private String familyTies;

    private String fileNumber;

    private String isDelete;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOneInstitutions(String oneInstitutions) {
        this.oneInstitutions = oneInstitutions;
    }

    public String getOneInstitutions() {
        return oneInstitutions;
    }

    public void setTwoInstitutions(String twoInstitutions) {
        this.twoInstitutions = twoInstitutions;
    }

    public String getTwoInstitutions() {
        return twoInstitutions;
    }

    public void setThreeInstitutions(String threeInstitutions) {
        this.threeInstitutions = threeInstitutions;
    }

    public String getThreeInstitutions() {
        return threeInstitutions;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setBirthpalce(String birthpalce) {
        this.birthpalce = birthpalce;
    }

    public String getBirthpalce() {
        return birthpalce;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setReligious(String religious) {
        this.religious = religious;
    }

    public String getReligious() {
        return religious;
    }

    public void setPoliticesStatus(String politicesStatus) {
        this.politicesStatus = politicesStatus;
    }

    public String getPoliticesStatus() {
        return politicesStatus;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setAverageEducationYears(String averageEducationYears) {
        this.averageEducationYears = averageEducationYears;
    }

    public String getAverageEducationYears() {
        return averageEducationYears;
    }

    public void setEducationMajor(String educationMajor) {
        this.educationMajor = educationMajor;
    }

    public String getEducationMajor() {
        return educationMajor;
    }

    public void setSalaryStandard(String salaryStandard) {
        this.salaryStandard = salaryStandard;
    }

    public String getSalaryStandard() {
        return salaryStandard;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHobby() {
        return hobby;
    }

    public void setIndividualResume(String individualResume) {
        this.individualResume = individualResume;
    }

    public String getIndividualResume() {
        return individualResume;
    }

    public void setFamilyTies(String familyTies) {
        this.familyTies = familyTies;
    }

    public String getFamilyTies() {
        return familyTies;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("oneInstitutions", getOneInstitutions())
                .append("twoInstitutions", getTwoInstitutions())
                .append("threeInstitutions", getThreeInstitutions())
                .append("positionType", getPositionType())
                .append("positionName", getPositionName())
                .append("position", getPosition())
                .append("name", getName())
                .append("sex", getSex())
                .append("email", getEmail())
                .append("phone", getPhone())
                .append("qq", getQq())
                .append("mobilePhone", getMobilePhone())
                .append("address", getAddress())
                .append("postcode", getPostcode())
                .append("nationality", getNationality())
                .append("birthpalce", getBirthpalce())
                .append("birthday", getBirthday())
                .append("ethnic", getEthnic())
                .append("religious", getReligious())
                .append("politicesStatus", getPoliticesStatus())
                .append("idCard", getIdCard())
                .append("socialSecurityNumber", getSocialSecurityNumber())
                .append("age", getAge())
                .append("educationBackground", getEducationBackground())
                .append("averageEducationYears", getAverageEducationYears())
                .append("educationMajor", getEducationMajor())
                .append("salaryStandard", getSalaryStandard())
                .append("openingBank", getOpeningBank())
                .append("account", getAccount())
                .append("registrant", getRegistrant())
                .append("createTime", getCreateTime())
                .append("specialty", getSpecialty())
                .append("hobby", getHobby())
                .append("individualResume", getIndividualResume())
                .append("familyTies", getFamilyTies())
                .append("remark", getRemark())
                .toString();
    }
}
