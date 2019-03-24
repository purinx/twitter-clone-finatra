const path = require('path');

module.exports = {
  mode: 'development',
  entry: {
    signup: './jsrc/signup.js',
    login: './jsrc/login.js',
    app: './jsrc/app.js',
  },
  output: {
    path: path.resolve('src/main/resources/view/js'),
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
    contentBase: './src/main/resources/view/js'
  }
};