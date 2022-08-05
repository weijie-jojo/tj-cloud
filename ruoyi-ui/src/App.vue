<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App',
    metaInfo() {
        return {
            title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
            titleTemplate: title => {
                return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
            }
        }
    }
}
</script>
<style lang="scss" scoped>
     ::v-deep .is-disabled{
    background-color: #F5F7FA !important;
   // border-color: black !important;
}
::v-deep .el-checkbox{
    color: black !important;
    background-color: transparent !important;
}
::v-deep .el-radio{
   background-color: #FFFFFF !important;
}
::v-deep .el-radio__input.is-checked .el-radio__inner{
  border-color: #1890ff !important;
    background: #1890ff !important;
}

::v-deep .el-checkbox__label{
     color: black !important;
}

::v-deep  .el-input__inner {
    background-color: transparent !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}

::v-deep .el-input__inner:hover {
   border-color: DeepSkyBlue !important;
}

::v-deep .el-textarea.is-disabled .el-textarea__inner {
  border-color: rgba(135,206,250,0.7) !important;
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black  !important;
}
::v-deep .el-radio__input.is-disabled + span.el-radio__label{ 
   background-color: transparent !important;
  color: black  !important;
}
.main-select-el-tree .el-tree-node .is-current > .el-tree-node__content{font-weight: bold; color: #409eff;}
.main-select-el-tree .el-tree-node.is-current > .el-tree-node__content{font-weight: bold; color: #409eff;}

::v-deep .el-dialog:not(.is-fullscreen) {
  margin-top: 1vh !important;
}
// ::v-deep .el-input.is-disabled .el-input__inner{
//    background-color: rgba(255, 255, 255, 1.5) !important;
//    color: black  !important;
//    border-color: rgba(135,206,250,0.7) !important;
// }
// ::v-deep .el-input-group__append{
//    background-color: rgba(255, 255, 255, 1.5) !important;
//    color: black  !important;
//    border-color: rgba(135,206,250,0.7) !important;
// }


</style>
