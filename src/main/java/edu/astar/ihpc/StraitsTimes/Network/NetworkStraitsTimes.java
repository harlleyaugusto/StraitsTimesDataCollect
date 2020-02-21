package edu.astar.ihpc.StraitsTimes.Network;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.astar.ihpc.StraitsTimes.database.dto.Comment;
import edu.astar.ihpc.StraitsTimes.database.dto.User;
import edu.astar.ihpc.StraitsTimes.database.model.CommentBO;
import edu.astar.ihpc.StraitsTimes.database.model.UserBO;

public class NetworkStraitsTimes {

	public static HashMap<User, HashMap<User, Float>> edges;
	public static List<Node> nodes;
	static Float addValeu = new Float(1);
	
	public static void main(String[] arg) throws IOException{
		buildNetwork();
		printNetwork();
		System.out.println(edges.size());
	}

	private static void printNetwork() throws IOException {
		// TODO Auto-generated method stub
		
		 	String gexf = "<?xml version='1.0' encoding='UTF-8'?>\n";
	        gexf += "<gexf xmlns='http://www.gexf.net/1.2draft' version='1.2'>\n";
	        gexf += "\t<graph mode='static' defaultedgetype='undirected'>\n";
	        gexf += "\t\t<attributes class='node'>\n";
	        gexf += "\t\t\t<attribute id='0' title='countLike' type='float'/>\n";
	        gexf += "\t\t\t<attribute id='1' title='countComment' type='float'/>\n";
	        gexf += "\t\t\t<attribute id='2' title='countNews' type='float'/>\n";
	        gexf += "\t\t</attributes>\n";
	        
	        gexf += "\t\t<nodes>";
	        for(Node n : nodes){
	        	gexf += n.printGephi();
	        }
	        gexf += "\n\t\t</nodes>\n";

	        gexf += "\t\t<edges>\n";
	        

	        Long countEdge = new Long(0);
	        for(User user1 : edges.keySet()){
	        	for(User user2 : edges.get(user1).keySet()){
	        		gexf +=  "\t\t\t<edge id='" + countEdge++ +"' source='"+ user1.getId() +"' target='"+ user2.getId() +"'/>\n";
	        	}
	        }

	        gexf += "\t\t</edges>\n";

	        gexf += "\t</graph>\n";
	        gexf += "</gexf>";

	        FileWriter fstream = new FileWriter("/home/harlley/Projects/DataCollect/data/Network.gexf");
	        BufferedWriter out = new BufferedWriter(fstream);
	        out.flush();
	        out.write(gexf);
	        out.flush();
		
	}

	private static void buildNetwork() {
		// TODO Auto-generated method stub
		List<Object> userList;
		
		UserBO userBo = new UserBO();
		CommentBO commentBo = new CommentBO();
		userList = userBo.load();
		edges = new HashMap<User, HashMap<User,Float>>();
		nodes = new ArrayList<Node>();
		
		for(Object oUser : userList){
			User user = (User) oUser;
			HashMap<User, Float> userEdges = new HashMap<User, Float>();
			
			Node node = new Node(user);
			nodes.add(node);
			
			List<Object> replyCommets = commentBo.getReplyByUser(user);
			if (replyCommets != null){
				for(Object oComment : replyCommets){
					Comment comment = (Comment) oComment;
					User userReplied = userBo.loadUser(comment.getReplyTo().getUser().getId());
					if(!userEdges.containsKey(userReplied)) userEdges.put(userReplied, addValeu);
					else userEdges.put(userReplied, userEdges.get(userReplied) + addValeu);
				}
				
			}
			edges.put(user, userEdges);
		}
	}
	
}
