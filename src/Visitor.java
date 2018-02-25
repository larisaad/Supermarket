
public interface Visitor {

	int visit(MusicDepartment dep);
	int visit(BookDepartment dep);
	int visit(VideoDepartment dep);
	int visit(SoftwareDepartment dep);
	
}
