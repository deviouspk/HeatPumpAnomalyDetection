import abstracts.Graph;
import graphs.heatpump.HeatPumpDataGraph;
import importer.CSVImporter;
import importer.transformers.HeatPumpDataPointTransformer2;
import importer.transformers.HeatPumpDataPointTransformer3;
import io.sentry.Sentry;
import io.sentry.SentryClientFactory;
import logic.LogicKernel;
import models.HeatPumpDataPoint;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void testHeatPumpData() throws IOException {
        CSVImporter<HeatPumpDataPoint> importer = new CSVImporter<>("/home/arthur/IdeaProjects/bynubiananomalydetection/data/normalData.csv", new HeatPumpDataPointTransformer2());

        List<HeatPumpDataPoint> data = importer.processImport();

        LogicKernel kernel = new LogicKernel(data);
        kernel.run();
    }

    public static void renderNormalHeatPumpGraph() throws IOException {
        CSVImporter<HeatPumpDataPoint> importer = new CSVImporter<>("/home/arthur/IdeaProjects/bynubiananomalydetection/data/normalData.csv", new HeatPumpDataPointTransformer2());
        List<HeatPumpDataPoint> list = importer.processImport();
        Graph graph = new HeatPumpDataGraph(list);
        graph.render();
    }

    public static void renderAbnormalHeatPumpGraph() throws IOException {
        CSVImporter<HeatPumpDataPoint> importer = new CSVImporter<>("/home/arthur/IdeaProjects/bynubiananomalydetection/data/abnormalData.csv", new HeatPumpDataPointTransformer3());
        List<HeatPumpDataPoint> list = importer.processImport();
        Graph graph = new HeatPumpDataGraph(list);
        graph.render();
    }

    public static void main(String... args) throws Exception {

        //initSentry();
        //renderNormalHeatPumpGraph();
        renderAbnormalHeatPumpGraph();
        //testHeatPumpData();
    }

    private static void initSentry() {
        Sentry.init("https://6b25bad8ea5644a0bfc1d25473c07bf2@sentry.io/1271900?sample.rate=0.75&environment=staging&servername=tonypc");
        SentryClientFactory.sentryClient();
    }
}
