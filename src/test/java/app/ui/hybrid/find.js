function fn(title) {
    let rows = locateAll('.border-bottom div');
    let count = rows.length;
    if (count) {
        let e = rows[count - 1];
        let text = e.script('_.textContent');
        if (text === title) {
            let root = e.parent.parent;
            let vals = root.script("_.getAttribute('hx-vals')");
            let obj = JSON.parse(vals);
            return obj.id;
        } else {
            return null;
        }
    } else {
        return null;
    }
}
