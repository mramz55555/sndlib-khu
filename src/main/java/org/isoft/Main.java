package org.isoft;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.util.List;

public class Main {
    private static BufferedWriter output;
    private static int lastIndex=-1;

    public static void main(String[] args) {
        try (BufferedWriter output =Main.output= new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\Topology.inc"))){
            //parsing xml
            JAXBContext context = JAXBContext.newInstance(Network.class, Node.class, Demand.class, Node.Coordinates.class,
                    Network.NetworkStructure.class, Network.Meta.class, Network.NetworkStructure.Link.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Network network = (Network) unmarshaller.unmarshal(Main.class.getResourceAsStream("/abilene.xml"));

            //writing in file

            output.write("set\n\nNode ID\t\t/");
            List<Node> nodes=network.getNetworkStructure().getNodes();
            lastIndex=nodes.size()-1;

            for (Node node : network.getNetworkStructure().getNodes()) {
                output.write(node.getId());
                writeComma(nodes,node);
            }
            output.write("/\n\nLink(Node,Node)\t/");

            List<Network.NetworkStructure.Link> links=network.getNetworkStructure().getLinks();
            lastIndex=links.size()-1;

            for (Network.NetworkStructure.Link link : links) {
                output.write(link.getSource() + "."+link.getTarget());
                writeComma(links,link);
            }
            output.write("/\n\nDemand_ID " + network.getDemands().size() + "\t/");

            List<Demand> demands = network.getDemands();
            lastIndex=demands.size()-1;
            for (Demand demand : demands) {
                output.write(demand.getSource() + "_" + demand.getTarget());
                writeComma(demands,demand);
            }

            output.write("/\n\n\n;\nParameter cost(link), capacity(link), demand(Demand_ID ),Source_Target(Node) ;\n\n");

            for (Network.NetworkStructure.Link link : links)
                output.write("cost1(" + link.getSource() + "," + link.getTarget() + ")=" + link.getPreInstalledModule().getCost() + "\n");

            for (Network.NetworkStructure.Link link : links)
                output.write("capacity1(" + link.getSource() + "," + link.getTarget() + ")=" + link.getPreInstalledModule().getCapacity() + "\n");

            for (Network.NetworkStructure.Link link : links)
                output.write("cost2(" + link.getSource() + "," + link.getTarget() + ")=" + link.getAdditionalModules().getAddModule().getCost() + "\n");

            for (Network.NetworkStructure.Link link : links)
                output.write("capacity2(" + link.getSource() + "," + link.getTarget() + ")=" + link.getAdditionalModules().getAddModule().getCapacity() + "\n");

            for (Demand demand : demands)
                output.write("demand(" + demand.getId() + ")=" + demand.getDemandValue() + ";Source_Target(" + demand.getSource() + ")=1;" + "Source_Target(" + demand.getTarget() + ")=-1);\n");

        } catch (JAXBException | IOException e) {
            System.err.println("An error occurred during operation======\n\n");
            e.printStackTrace();
        }
        System.out.println("Program finished successfully");
    }

    private static <T> void writeComma(List<T> list,T object) throws IOException{
        output.write((list.indexOf(object)==lastIndex) ? "" : ",");
    }
}

