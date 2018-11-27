<template>

      <div class="container" style="height:700px;padding-left: 80px;padding-right: 80px;">
        <AD></AD>
        <h1  >博客列表</h1>
          <article class="column is-3"   v-for="blog in blogs" v-bind:key="blog"> 
            <a   class="bd-article-image is-bootstrap" >
            <span class="bd-article-overlay"></span>
            <span class="bd-article-icon">
                <i class="fa fa-tag"></i>
            </span>
            <strong class="bd-star-icon" ><i class="fa fa-star"></i> <span style="font-size: 1rem">&nbsp;{{blog.commendCount}}</span></strong>
            <strong class="bd-article-info">
                <span>
                <time class="bd-article-date" datetime="2017-10-09T00:00:00+00:00">
                    {{blog.tag}}
                </time>
                <strong class="bd-article-title">
                    {{blog.title}}
                </strong>
                </span>
            </strong>
            </a>
        </article>
      </div>
</template>


<script>
import AD from "@/components/common/AD";
let article = { tag: "java", title: "java", commendCount: 0 };
export default {
  name: "Content",
  components: { AD },
  data() {
    return {
      blogs: [
        article,
        article,
        article,
        article,
        article,
        article,
        article,
        article
      ]
    };
  },
  created() {},
  destroyed() {},
  mounted() {},
  methods: {}
};
</script>

<style scoped>
.bd-article-image.is-bootstrap {
  background-color: #6f5499;
}
.bd-article-image {
  background-color: #00d1b2;
  display: block;
  height: 240px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  text-align: center;
}

.bd-star-icon {
  font-size: 19.2px;
  font-size: 1.2rem;
  color: #0a0a0a;
  opacity: 0.25;
  bottom: 10px;
  left: 30px;
  position: absolute;
  -webkit-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  align-items: center;
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
}
.bd-article-icon,
.bd-article-info {
  bottom: 0;
  left: 0;
  position: absolute;
  right: 0;
  top: 0;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
}
.bd-article-info {
  padding: 20px;
}
a strong {
  color: currentColor;
}
.bd-article-date {
  color: rgba(0, 0, 0, 0.5);
  display: block;
}
.bd-article-title {
  color: white;
  display: block;
  font-size: 1.8rem;
  font-weight: 700;
  line-height: 1.25;
  padding: 0 20px;
}
.bd-article-icon {
  color: #0a0a0a;
  opacity: 0.25;
}
h1 {
  text-align: center;
  font-size: 30px;
}

.column.is-3,
.column.is-3-tablet {
  -webkit-box-flex: 0;
  -ms-flex: none;
  flex: none;
  width: 25%;
  float: left;
}
</style>

