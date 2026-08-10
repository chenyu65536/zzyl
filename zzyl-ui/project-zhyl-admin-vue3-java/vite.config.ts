import path from 'path'
import { ConfigEnv, UserConfig, loadEnv } from 'vite'
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
      svgLoader()
    ],
    server: {
      port: 3001,
      host: '0.0.0.0',
      hmr: true,
      proxy: {
        '/api': {
          target: 'http://localhost:8080',
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
