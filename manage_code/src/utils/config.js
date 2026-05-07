const config = {
  get() {
    return {
      url: process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
    }
  },
  getProjectName() {
    return {
      projectName: `基于SpribgBoot的社区团购系统`,
    }
  },
}
export default config
