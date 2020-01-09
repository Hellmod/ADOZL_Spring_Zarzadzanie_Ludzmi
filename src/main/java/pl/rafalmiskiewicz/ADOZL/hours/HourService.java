package pl.rafalmiskiewicz.ADOZL.hours;
import java.util.List;


public interface HourService {
	
	public List<Hour> findAll();
	public List<Hour> findHourById(int id);
	public List<Hour> findAllByUserId(int id);

}
