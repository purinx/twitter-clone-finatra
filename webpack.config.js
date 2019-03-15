const path = require('path');

module.exports = {
  mode: 'development',
  entry: {
    home:'./jsrc/pages/home.js',
    profile:'./jsrc/pages/profile.js'
  },
  output: {
    path: path.resolve('src/main/resources/js'),
    filename: '[name].js'
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        use: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.css$/,
        use: [
          'style-loader',
          'css-loader'
        ]
      }
    ]
  },
  resolve: {
    extensions: [
      '.js',
      '.jsx'
    ]
  },
  devServer: {
    contentBase: './dist'
  }
};