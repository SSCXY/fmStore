new Vue({
    el:"#app",
    data:{
        brandList:[],
        brand:{
            name : '',
            firstChar : ''
        },
        searchBrands :{
            name : '',
            firstChar : ''
        },
        isFlash : false,//防止没id时点击删除也发送请求
        page : 1,
        pageSize : 10,
        total : 150,
        maxPage : 9,
        selectIds : []
    },
    methods : {
          pageHandler : function (page) {
              var _this = this;
              _this.page = page;
              axios.post('/brand/findPage.do?page='+this.page+"&rows=10" ,_this.searchBrands)
                  .then(function (response) {
                      _this.brandList = response.data.rows;
                      _this.total = response.data.total;
                  }).catch(function (reason) {
                      console.log(reason);
              })
          },
          brandSave : function () {
              var _this = this;
              if (_this.brand.id == null){
                  axios.post('/brand/add.do', _this.brand)
                      .then(function (response) {
                          if (response.data.success){
                              console.log(response.data.message);
                              _this.pageHandler(_this.page);
                              _this.brand = {};
                          }else {
                              console.log(response.data.message);
                          }
                      }).catch(function (reason) {
                      console.log(reason);
                  })
              } else {
                  console.log(_this.brand);
                  axios.post('/brand/update.do', _this.brand)
                      .then(function (response) {
                            _this.pageHandler(_this.page);
                            _this.brand = {};
                      })
              }

          },
          findById : function (id) {

              var _this = this;
              axios.get('/brand/findById.do', {params:{id:id}})
                  .then(function (response) {
                      _this.brand = response.data;
                  }).catch(function (reason) {
                      console.log(reason);
              })
          },
          deleteSelection : function (event, id) {
              if (event.target.checked){
                  this.selectIds.push(id);
              }else {
                  var ids = this.selectIds.indexOf(id);
                  /**
                   *  splice(index,howmany,item1,.....,itemX)
                   *  第一个参数index是数组元素位置，第二个元素
                   *  是删除几个，后面可选
                   */
                  this.selectIds.splice(ids, 1);
              }
              this.isFlash = true;
          },
          deleteBrand : function () {
              console.log(this.selectIds);
              var _this = this;
              if (_this.isFlash === true) {
                  axios.post('/brand/delete.do', Qs.stringify({ids : _this.selectIds}, {indices : false}))
                      .then(function () {
                          _this.pageHandler(1);
                          _this.selectIds = [];
                          window.location.reload();
                          _this.isFlash = false;
                      }).catch(function (reason) {
                      console.log(reason.message);
                  })
              }
          }
    },
    created : function () {
        this.pageHandler(1);
    }

});