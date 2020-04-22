package cn.nextop.guava.widgets.table.model.column;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
public class Columns {
	//
	protected List<Column<?>> colums;
	
	public Columns() {
		this.colums = new ArrayList<>();
	}
	
	public void add(Column<?> colum) {
		this.colums.add(colum);
	}

	public List<Column<?>> getColumns() {
		return colums;
	}
	
	public List<Column<?>> toPixel(int width) {
		int tw = 0, tp = 0; 
		for (Column<?> col : colums) {
			final int px = col.getPixel(), wt = col.getWeight();
			if(wt > 0) { tw = tw + wt; } else { tp = tp + px; }
		}
		if(tw > 0) { 
			int rp = width - tp, aw = rp / tw, idx = 0;
			for (Column<?> col : colums) {
				final int px = col.getPixel();
				final int wt = col.getWeight();
				if(wt == 0 || aw * wt < px) continue;
				
				if(idx == colums.size() - 1) col.setPixel(rp);
				else { col.setPixel(aw * col.getWeight()); }
				rp = rp - aw * col.getWeight();
			}
		}
		return colums;
	}
}
