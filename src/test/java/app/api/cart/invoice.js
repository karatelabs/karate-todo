function(items, taxRate) {
  var subtotal = 0;
  items.forEach(item => subtotal += item.price);
  var tax = subtotal * taxRate;
  return { subtotal: subtotal, tax: tax, total: subtotal + tax, lines: items.length };
}
