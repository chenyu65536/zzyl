import path from 'path'
import { ConfigEnv, UserConfig, loadEnv } from 'vite'
import { viteMockServe } from 'vite-plugin-mock'
import createVuePlugin from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import svgLoader from 'vite-svg-loader'

const CWD = process.cwd()

// https://vitejs.dev/config/
export default ({ mode }: ConfigEnv): UserConfig => {
  const { VITE_BASE_URL } = loadEnv(mode, CWD)
  return {
    base: VITE_BASE_URL,
    define: {},
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src')
      }
    },

    css: {
      preprocessorOptions: {
        less: {
          modifyVars: {
            hack: `true; @import (reference) "${path.resolve(
              'src/style/variables.less'
            )}";`
          },
          math: 'strict',
          javascriptEnabled: true
        }
      }
    },

    plugins: [
      createVuePlugin(),
      vueJsx(),
      viteMockServe({
        mockPath: 'mock',
        localEnabled: false, // 是否开启本地mock 我们用的远程连接 直接关闭 走代理就OK
        // 修改点：生产环境关闭 mock。原 prodEnabled:true 会把 mock 服务打进生产包，
        // setupProdMockServer() 会拦截/伪造后端接口响应，导致生产环境数据错乱与安全隐患。
        prodEnabled: false,
        supportTs: true,
        logger: true,
        injectCode: `
          import { setupProdMockServer } from '../mockProdServer';
          setupProdMockServer();
        `
      }),
      svgLoader()
    ],
    server: {
      port: 3001,
      host: '0.0.0.0',
      hmr: true,
      proxy: {
        '/api': {
          target: 'http://localhost:9995',
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    },
    // 修改点：新增构建产物分包，解决原先单 chunk 体积过大（约 1.69MB）导致的首屏加载慢、
    // 无法利用浏览器缓存等问题。将 echarts 单独拆包，其余第三方依赖归入 vendor，
    // 业务代码保持独立，提升缓存命中率与加载性能。
    build: {
      rollupOptions: {
        output: {
          manualChunks(id: string) {
            if (id.includes('node_modules')) {
              if (id.includes('echarts') || id.includes('zrender')) {
                return 'echarts'
              }
              return 'vendor'
            }
          }
        }
      }
    }
  }
}
