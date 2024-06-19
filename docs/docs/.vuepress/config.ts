import { defaultTheme } from '@vuepress/theme-default'
import { defineUserConfig } from 'vuepress/cli'
import { viteBundler } from '@vuepress/bundler-vite'

const sidebar: NavbarConfig = [
  {
    text: 'Get Started',
    children: [
      '/get-started.md'
    ]
  },
  {
    text: 'Compose',
    children: [
      '/compose/introduction.md',
      '/compose/colors.md',
      '/compose/permission.md',
      '/compose/remember.md',
      '/compose/ui.md'
    ]
  }
]

export default defineUserConfig({
  lang: 'en-US',

  title: 'dev.medzik.android',
  description: 'Android Utilities Library',

  theme: defaultTheme({
//     logo: 'https://vuejs.press/images/hero.png',

    navbar: ['/', '/get-started'],
    sidebar: sidebar
  }),

  bundler: viteBundler(),
})
