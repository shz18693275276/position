<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">
      <el-form-item label="Ⅰ级机构" prop="roleName" >
        <el-select v-model="form.oneInstitutions" placeholder="请选择Ⅰ级机构"   @keyup.enter.native="handleQuery">
          <el-option v-for="dict in onePositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
        </el-select>

      </el-form-item>
      <el-form-item label="ⅠⅠ级机构" prop="roleName">
        <el-select v-model="form.twoInstitutions" placeholder="ⅠⅠ级机构"   @keyup.enter.native="handleQuery">
          <el-option v-for="dict in twoPositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
        </el-select>

      </el-form-item>
      <el-form-item label="ⅠⅠⅠ级机构" prop="roleName">
        <el-select v-model="form.threeInstitutions" placeholder="ⅠⅠⅠ级机构"   @keyup.enter.native="handleQuery">
          <el-option v-for="dict in threePositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item label="姓名" prop="roleName">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职称" prop="roleKey">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入权限字符"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          style="margin-left: 16px;"
          type="primary"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="addHandler"

          v-hasPermi="['system:role:remove']"
        >登记
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:role:remove']"
        >批量删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="updateStatus"
          v-hasPermi="['system:role:remove']"
        >批量复核
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:role:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="roleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="档案编号" prop="fileNumber" width="230"/>
      <el-table-column label="姓名" prop="name" :show-overflow-tooltip="true" width="210"/>
      <el-table-column label="Ⅰ级机构" prop="oneInstitutions" :formatter="onePositionStatus" :show-overflow-tooltip="true" width="200"/>
      <el-table-column label="ⅠⅠ级机构" prop="twoInstitutions" :formatter="twoPositionStatus" :show-overflow-tooltip="true" width="200"/>
      <el-table-column label="ⅠⅠⅠ级机构" prop="threeInstitutions" :formatter="threePositionStatus" :show-overflow-tooltip="true" width="200"/>
      <el-table-column label="职称" prop="positionName"  width="100"/>
      <el-table-column label="修改" align="center" width="100" >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status == '1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateStatus(scope.row)"
            v-hasPermi="['system:role:edit']"
          >修改
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


    <el-drawer
      title="档案建立"
      :visible.sync="drawer"
      direction="rtl"
      ref="drawer"
      size="80%"
    >
      <div class="demo-drawer__content">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="Ⅰ级机构" prop="oneInstitutions">

                <el-select v-model="form.oneInstitutions" placeholder="请选择Ⅰ级机构" v-if="status=='1'"  disabled >
                  <el-option v-for="dict in onePositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>
                <el-select v-model="form.oneInstitutions" placeholder="请选择Ⅰ级机构" v-if="status=='0'"   >
                  <el-option v-for="dict in onePositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="ⅠⅠ级机构" prop="twoInstitutions">
                <el-select v-model="form.twoInstitutions" placeholder="请选择ⅠⅠ级机构" v-if="status=='1'"  disabled >
                  <el-option v-for="dict in twoPositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>
                <el-select v-model="form.twoInstitutions" placeholder="请选择ⅠⅠ级机构" v-if="status=='0'"   >
                  <el-option v-for="dict in twoPositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="ⅠⅠⅠ级机构" prop="threeInstitutions">
                <el-select v-model="form.threeInstitutions" placeholder="请选择ⅠⅠ级机构" v-if="status=='1'"  disabled >
                  <el-option v-for="dict in threePositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>
                <el-select v-model="form.threeInstitutions" placeholder="请选择ⅠⅠ级机构" v-if="status=='0'"   >
                  <el-option v-for="dict in threePositionOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="职位分类" prop="positionType">
                <el-select v-model="form.positionType" placeholder="请选择ⅠⅠ级机构" v-if="status=='1'"  disabled >
                  <el-option v-for="dict in classificationsOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>
                <el-select v-model="form.positionType" placeholder="请选择ⅠⅠ级机构" v-if="status=='0'"   >
                  <el-option v-for="dict in classificationsOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                </el-select>

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="职位名称" prop="positionName" style="width: 320px">
                <el-input v-model="form.positionName" placeholder="请输入职位名称" v-if="status == '0'"></el-input>
                <el-input v-model="form.positionName" placeholder="请输入职位名称" v-if="status == '1'" readonly="true"> </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="职称" prop="position" style="width: 320px">
                <el-input v-model="form.position" placeholder="请输入职称" v-if="status == '0'"></el-input>
                <el-input v-model="form.position" placeholder="请输入职称" v-if="status == '1'" readonly="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="姓名" prop="name" style="width: 320px">
                <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="性别" prop="sex">
                <el-select v-model="form.sex" placeholder="请选择性别">
                  <el-option label="男" value="0"></el-option>
                  <el-option label="女" value="1"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="Email" style="width: 320px" prop="email">
                <el-input v-model="form.email" placeholder="请输入Email"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="电话" prop="phone" style="width: 320px">
                <el-input v-model="form.phone" placeholder="请输入电话"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="qq" prop="positionName" style="width: 320px">
                <el-input v-model="form.qq" placeholder="请输入qq"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="手机" prop="mobilePhone" style="width: 320px">
                <el-input v-model="form.mobilePhone" placeholder="请输入手机号"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="16">
              <el-form-item label="住址" prop="address">
                <el-input v-model="form.address" placeholder="请输入住址"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="邮编" prop="postcode">
                <el-input v-model="form.postcode" placeholder="请输入邮编"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="国籍" prop="nationality">
                <el-select v-model="form.nationality" placeholder="请选择国籍">
                  <el-option
                    v-for="item in countryList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="出生地" prop="birthpalce">
                <el-input v-model="form.birthpalce" placeholder="请输入出生地"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="生日" prop="birthday">
                <el-input v-model="form.birthday" placeholder="请输入生日"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="民族" prop="ethnic">
                <el-select v-model="form.ethnic" placeholder="请选择名族">
                  <el-option
                    v-for="item in nationalityList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="宗教信仰" prop="religious">
                <el-select v-model="form.religious" placeholder="请选择宗教信仰">
                  <el-option
                    v-for="dict in religiousOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="政治面貌" prop="politicesStatus">
                <el-select v-model="form.politicesStatus" placeholder="请选择政治面貌">
                  <el-option label="群众" value="0"></el-option>
                  <el-option label="团员" value="1"></el-option>
                  <el-option label="党员" value="2"></el-option>
                </el-select>

              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="身份证号" prop="idCard">
                <el-input v-model="form.idCard" placeholder="请输入身份证号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="社会保障号" prop="socialSecurityNumber">
                <el-input v-model="form.socialSecurityNumber" placeholder="请输入社会保障号"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="年龄" prop="age">
                <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="学历" prop="educationBackground">
                <el-select v-model="form.educationBackground" placeholder="请选择学历">
                  <el-option
                    v-for="dict in studyOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>

              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="教育年限" prop="averageEducationYears">
                <el-input v-model="form.averageEducationYears" placeholder="请输入教育年限"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="学历专业" prop="educationMajor" >
                <el-input v-model="form.educationMajor" placeholder="请输入学历专业"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="薪酬标准" prop="salaryStandard">
                <el-select v-model="form.salaryStandard" placeholder="请选择薪酬标准">
                  <el-option
                    v-for="dict in paymentOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="开户行" prop="openingBank">
                <el-input v-model="form.openingBank " placeholder="请输入开户行"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="账号" prop="account">
                <el-input v-model="form.account" placeholder="请输入账号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="特长" prop="specialty">
                <el-input v-model="form.specialty" placeholder="请输入特长，至少一个"></el-input>

              </el-form-item>
            </el-col>
          </el-row>
          <el-row>

            <el-col :span="6">
              <el-form-item label="爱好" prop="hobby">
                <el-input v-model="form.hobby" placeholder="请输入爱好，至少一个"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="个人履历" prop="individualResume">
            <el-input type="textarea" v-model="form.individualResume"></el-input>
          </el-form-item>
          <el-form-item label="家庭关系信息" prop="familyTies">
            <el-input type="textarea" v-model="form.familyTies"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-if="status=='0'"  @click="submitForm">登记</el-button>
            <el-button type="primary" v-if="status=='1'"  @click="submitForm">复核</el-button>
            <el-button @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>

  </div>
</template>

<script>
  import {
    listPosition,
    queryPosition,
    addPosition,
    updatePosition,
    delPosition,
    getCountry,
    upateStatus
  } from "@/api/position/position";


  export default {
    name: "Role",
    data() {
      return {
        drawer:false,
        dialog: false,
        queryParams:{},
        total: 0,
        form:{},
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 表单校验
        roleList:[],
        ids:[],
        //一级机构
        onePositionOptions:[],
        //二级机构
        twoPositionOptions:[],
        //三级机构
        threePositionOptions:[],
        //职业分类
        classificationsOptions:[],
        //国籍
        countryList:[],
        //宗教
        religiousOptions:[],
        //民族
        nationalityList:[],
        //学历
        studyOptions:[],
        //薪酬标准
        paymentOptions:[],
        //审核状态
        status:'0',
        //批量复核状态
        rules: {
          roleName: [
            {required: true, message: "角色名称不能为空", trigger: "blur"}
          ],
          roleKey: [
            {required: true, message: "权限字符不能为空", trigger: "blur"}
          ],
          roleSort: [
            {required: true, message: "角色顺序不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("position_one").then(response => {
        this.onePositionOptions = response.data;
      });
      this.getDicts("position_two").then(response => {
        this.twoPositionOptions = response.data;
      });
      this.getDicts("position_three").then(response => {
        var that = this;
        that.threePositionOptions = response.data;

      });
      this.getDicts("classifications").then(response => {
        var that = this;
        that.classificationsOptions = response.data;
      });
      this.getDicts("religious_type").then(response => {
        var that = this;
        that.religiousOptions = response.data;
      });
      this.getDicts("study_type").then(response => {
        var that = this;
        that.studyOptions = response.data;
      });
      this.getDicts("payment_type").then(response => {
        var that = this;
        that.paymentOptions = response.data;
      });
    },
    methods: {

      /** 查询角色列表 */
      getList() {
        this.loading = true;
        listPosition(this.queryParams).then(response => {
            this.roleList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
        getCountry().then(res =>{
          var that = this;
          that.countryList = res.data.country;
          that.nationalityList = res.data.nationality;
        })
      },

      updateStatus(row){
        const postIds = row.id || this.ids;
        this.$confirm('是否确要提交复核"' + postIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return upateStatus(postIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },

      // 字典状态字典翻译
      onePositionStatus(row, column) {
        return this.selectDictLabel(this.onePositionOptions, row.oneInstitutions);
      },
      twoPositionStatus(row, column) {
        return this.selectDictLabel(this.twoPositionOptions, row.twoInstitutions);
      },
      threePositionStatus(row, column) {
        return this.selectDictLabel(this.threePositionOptions, row.threeInstitutions);
      },


      addHandler(){
        this.drawer = true;
        this.status = '0';
      },
      cancel(){
        this.drawer = false;
      },
      handleUpdate(row){
        this.drawer = true
        this.status = '1'

        queryPosition(row.id).then(res =>{
          this.form = res.data;
        })
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const postIds = row.id || this.ids;
        this.$confirm('是否确认删除岗位编号为"' + postIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delPosition(postIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 提交按钮 */
      submitForm: function () {
        var that = this;
        that.$refs["form"].validate(valid => {
          if (valid) {
            if (that.form.id != undefined && that.form.id != '' ) {
               updatePosition(this.form).then(response => {
                 this.msgSuccess("修改成功");
                 this.drawer = false;
                 this.getList();
               });
            } else {
              addPosition(that.form).then(response => {
                this.msgSuccess("新增成功");
                this.drawer = false;
                this.getList();
              });
            }
          }
        });
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },

      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有岗位数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportPost(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      },


    }
  };
</script>
<style>
  .el-drawer__body {
    overflow: auto;
    /* overflow-x: auto; */
  }


  /*2.隐藏滚动条，太丑了*/
  .el-drawer__container ::-webkit-scrollbar{
    display: none;
  }
</style>
