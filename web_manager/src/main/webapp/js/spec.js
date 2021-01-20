new Vue({
   el : "#app",
    data: {
        specList: [],
        spec: {
            name: '',
            firstChar: ''
        },
        searchSpec: {
            name: '',
            firstChar: ''
        },
        specOption:[], //规格选项
        searchObj:{},
        isFlash: false,//防止没id时点击删除也发送请求
        page: 1,
        pageSize: 10,
        total: 150,
        maxPage: 9
    },
    methods : {
        pageHandler : function(page){
            this.page = page;
            var _this = this;
            axios.post('/spec/search.do?page='+this.page+"&pageSize="+this.pageSize, this.searchObj)
                .then(function (response) {
                    _this.specList = response.data.rows;
                    _this.total = response.data.total;
                    console.log(_this.specList);
                }).catch(function (reason) {
                    console.log(reason);
            })
        },
        addRow : function () {
            this.specOption.push({});
        }
    },
    created : function () {
        this.pageHandler(1);
    }
});