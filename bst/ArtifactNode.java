package bst;

import model.Artifact;

public class ArtifactNode {

    Artifact data;
    ArtifactNode left;
    ArtifactNode right;

    ArtifactNode(Artifact a) {
        this.data = a;
        this.left = null;
        this.right = null;
    }
}
