package org.isoft;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "network")
@XmlAccessorType(XmlAccessType.FIELD)
public class Network {
    @XmlElement
    private Meta meta;
    @XmlElement
    private NetworkStructure networkStructure;
    @XmlElementWrapper(name = "demands")
    @XmlElement(name = "demand")
    private List<Demand> demands = new ArrayList<>();

    @Override
    public String toString() {
        return "Network{" +
                "meta=" + meta +
                ", networkStructure=" + networkStructure +
                ", demands=" + demands +
                "}\n";
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Meta {
        @XmlElement
        private String granularity;
        @XmlElement
        private String time;
        @XmlElement
        private String unit;
        @XmlElement
        private String origin;

        @Override
        public String toString() {
            return "Meta{" +
                    "granularity='" + granularity + '\'' +
                    ", time='" + time + '\'' +
                    ", unit='" + unit + '\'' +
                    ", origin='" + origin + '\'' +
                    "}\n";
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class NetworkStructure {
        @XmlAttribute
        private String coordinatesType;
        @XmlElementWrapper(name = "nodes")
        @XmlElement(name = "node")
        private List<Node> nodes = new ArrayList<>();
        @XmlElementWrapper(name = "links")
        @XmlElement(name = "link")
        private List<Link> links = new ArrayList<>();

        @Override
        public String toString() {
            return "NetworkStructure{" +
                    "coordinatesType='" + coordinatesType + '\'' +
                    ", nodes=" + nodes +
                    ", links=" + links +
                    "}\n";
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Link {
            @XmlAttribute
            private String id;
            @XmlElement
            private String source;
            @XmlElement
            private String target;
            @XmlElement
            private PreInstalledModule preInstalledModule;
            @XmlElement
            private AdditionalModules additionalModules;

            @Override
            public String toString() {
                return "Link{" +
                        "id='" + id + '\'' +
                        ", source='" + source + '\'' +
                        ", target='" + target + '\'' +
                        "}\n";
            }

            @NoArgsConstructor
            @AllArgsConstructor
            @Getter
            @Setter
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class InnerProps {
                @XmlElement
                protected String capacity;
                @XmlElement
                protected String cost;

                @Override
                public String toString() {
                    return "InnerProps{" +
                            "capacity='" + capacity + '\'' +
                            ", cost='" + cost + '\'' +
                            "}\n";
                }
            }

            public static class PreInstalledModule extends InnerProps {
            }

            public static class AddModule extends InnerProps {
            }

            @NoArgsConstructor
            @AllArgsConstructor
            @Getter
            @Setter
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class AdditionalModules {
                @XmlElement
                private AddModule addModule;
            }
        }
    }
}
