 <template>
  <div class="Blog">
    <h1>{{ msg }}</h1>
     
  <div v-for="(blog,index) in blogList" v-bind:key="index">
    <h3 >
      <a :href="`https://juejin.im/book/`+blog.id" >
        <span>{{blog.title}}</span>
      </a>
      
      </h3>
  </div>
     
  </div>
</template>



<script>
import juejin from "@/api/juejin";
export default {
  name: "Blog",
  data() {
    return {
      msg: "掘金小册一览",
      blogList: []
    };
  },
  created() {
    this.getBlog();
  },
  methods: {
    getBlog() {
      // this.$http.get("/v1/getListByLastTime?src=web&pageNum=1").then(res => {
      //   this.blogList = res.data.d;
      // });
      juejin.getBlog("1", response => {
        this.blogList = response.d;
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
