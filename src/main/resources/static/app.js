var data = [
    {
        "title" : "Session Management using Spring Session with JDBC DataStore",
        "url": "https://sivalabs.in/2018/02/session-management-using-spring-session-jdbc-datastore/",
        "tags": ["java","spring","spring-boot", "spring-session"]
    },
    {
        "title" : "CI/CD for SpringBoot applications using Travis-CI",
        "url": "https://sivalabs.in/2018/01/ci-cd-springboot-applications-using-travis-ci/",
        "tags": ["java","travis-ci","spring-boot"]
    }

];

var app2 = new Vue({
    el: '#app',
    data: {
        data: data,
        query: '',
        searchTag: ''
    },
    methods: {
        addSearchTag : function(tag) {
            this.searchTag = tag;
        },
        clearFilters: function() {
            this.searchTag = '';
            //this.query = '';
        }
    },
    computed: {
        filteredData () {
            //const { title, url, tags } = this
            var searchTagFilteredData = this.data
                .filter(item =>  this.searchTag=='' || item.tags.indexOf(this.searchTag) > -1);
            return searchTagFilteredData
                    .filter(item => item.title.toLowerCase().indexOf(this.query.toLowerCase()) > -1 ||
                item.url.toLowerCase().indexOf(this.query.toLowerCase()) > -1 ||
                item.tags.join().toLowerCase().indexOf(this.query.toLowerCase()) > -1
        )

        },
        allTags () {
            var tags = [];
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                for (var j = 0; j < item.tags.length; j++) {
                    var tag = item.tags[j];
                    if(tags.indexOf(tag) == -1) {
                        tags.push(tag);
                    }
                }
            }
            return tags;
        }
    }
});