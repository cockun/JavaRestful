function formatDollar(num) {
    var p = num.toFixed(0).split('.');
    return p[0]
      .split('')
      .reverse()
      .reduce(function (acc, num, i, orig) {
        return num == '-' ? acc : num + (i && !(i % 3) ? ',' : '') + acc;
      }, '');
 }
