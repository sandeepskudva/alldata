package NoticeCreation;



public class distribute {
	public static void main (String args[]) 
	{
	
		String lane_tx_id[] = {"101","102","103","104","105","106","107","108","109","110","111","112","113","114","115"};
		String platenumber[] ={"a1","a2","a3"};
		int j=0;
		int k=0;
		int number = lane_tx_id.length/platenumber.length;
		for(int i=0;i<lane_tx_id.length;i++)
		{
			if(j==number)
			{
				j=0;
				
				k++;
			}
			j++;
			lane_tx_id[i]=lane_tx_id[i]+platenumber[k];
			
			System.out.println(lane_tx_id[i]);
		}		
		             

	}
}
