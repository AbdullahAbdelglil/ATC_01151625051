/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        areeb: {
          primary: '#297EFF',
          dark: '#0A0A23',
          light: '#FFFFFF',
        },
      },
      keyframes: {
        fadeIn: {
          '0%': { opacity: '0', transform: 'translateY(10px)' },
          '100%': { opacity: '1', transform: 'translateY(0)' },
        },
      },
      animation: {
        fadeIn: 'fadeIn 0.5s ease forwards',
      },
    },
  },
  plugins: [
    require('@tailwindcss/line-clamp'),
  ],
}
