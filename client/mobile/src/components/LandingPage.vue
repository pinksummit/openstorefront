<template lang="html">

  <section>
    <div class="logo-wrapper grey darken-3" v-if="$store.state.branding.applicationName === 'SPOON'">
      <img class="logo" src="../assets/img/SPOON_logo.png" width="300" alt="SPOON Logo">
    </div>

    <SearchBar v-on:submitSearch="submitSearch()" v-model="searchQuery" class="mx-3 my-4"></SearchBar>

    <div style="margin: 0 1.5em;">
      <div style="max-width: 36em; margin: 0 auto;">

      <h2 style="margin-bottom: 1em;">Browse Categories</h2>

      <v-list v-for="item in nestedComponentTypesList.children" :key="item.componentType.label">
        <v-list-tile>
          <div style="float: left;" v-if="item.componentType.iconUrl">
            <img :src="'/openstorefront/' + item.componentType.iconUrl" width="35" style="margin-right: 1em;">
          </div>
          <v-list-tile-content>

          <router-link :to="{ path: 'search', query: { comp: item.componentType.componentType, children: true }}" style="width: 100%; text-decoration: none;">
              {{ item.componentType.label }}
          </router-link>

          </v-list-tile-content>
        </v-list-tile>
        <v-divider></v-divider>
      </v-list>
      </div>
    </div>

    <v-footer color="primary" dark height="auto">
      <v-card color="primary" dark flat class="footer-wrapper">
        <div v-html="$store.state.branding.landingPageFooter"></div>
      </v-card>
    </v-footer>

  </section>

</template>

<script lang="js">
import SearchBar from './subcomponents/SearchBar';
import axios from 'axios';

export default {
  name: 'landing-page',
  components: {
    SearchBar
  },
  props: [],
  mounted () {
    this.getNestedComponentTypes();
  },
  data () {
    return {
      searchQuery: '',
      nestedComponentTypesList: [],
      errors: []
    };
  },
  methods: {
    link (query) {
      return `/search?q=${query}`;
    },
    submitSearch () {
      this.$router.push(`/search?q=${this.searchQuery}`);
    },
    getNestedComponentTypes () {
      axios
        .get(
          '/openstorefront/api/v1/resource/componenttypes/nested'
        )
        .then(response => {
          this.nestedComponentTypesList = response.data;
        })
        .catch(e => this.errors.push(e));
    }
  },
  computed: {

  }
};
</script>

<style scoped lang="scss">
.shadow {
  box-shadow: 0 3px 1px -2px rgba(0,0,0,.2),0 2px 2px 0 rgba(0,0,0,.14),0 1px 5px 0 rgba(0,0,0,.12);
}
.link-tile:hover {
  background-color: rgba(0,0,0,0.2);
}
.logo-wrapper {
  max-width: 100%;
  text-align: center;
  margin-bottom: 2em;
}
.logo {
  padding: 2em;
  max-width: 100%;
}
.footer-wrapper {
  width: 100%;
  margin-left: auto;
  margin-right: auto;
}
</style>
