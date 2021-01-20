new Vue({
    el:"#app",
    data:{
        brandList:[],
        brand:{
            name : '',
            firstChar : ''
        },
        page : 1,
        pageSize : 10,
        total : 150,
        maxPage : 9
    },
    methods : {
          pageHandler : function (page) {
              var _this = this;
              _this.page = page;
              axios.get('/brand/findPage.do', {params:{page : page, rows : this.pageSize}})
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
                              alert(response.data.message);
                              _this.pageHandler(_this.page);
                              _this.brand = {};
                          }else {
                              alert(response.data.message);
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
          }
    },
    created : function () {
        this.pageHandler(1);
    }

});