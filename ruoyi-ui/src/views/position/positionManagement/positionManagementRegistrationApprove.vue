<template>
  <div>
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
            v-hasPermi="['system:role:edit']"
          >修改
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
  import {
    listPosition,
  } from "@/api/position/position";


  export default {
    name: "Role",
    data() {
      return {

        // 表单校验
        roleList:[],
        ids:[],
        //一级机构
        onePositionOptions:[],
        //二级机构
        twoPositionOptions:[],
        //三级机构
        threePositionOptions:[],

        status:'0',

      };
    },
    created() {
      this.getList();

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



      cancel(){
        this.drawer = false;
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


    }
  };
</script>
