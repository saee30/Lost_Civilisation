package bst;
import model.Artifact;

public class ArtifactBST {
    
    private ArtifactNode root;
    private int artifactCount;

    public ArtifactBST() {

        this.root = null;
        this.artifactCount = 0;
    }

    public boolean insert(Artifact artifact) {
        
        if(artifact == null) { //checking if the artifact is null

            return false;
        }

        if (root == null) { //if the tree is empty, making the new artifact its root

            root = new ArtifactNode(artifact);
            artifactCount++;
            return true;
        }
        
        ArtifactNode curr = root;

        while (true) {

            if(artifact.getArtifactId() < curr.data.getArtifactId()) { //comparing artifact IDs

                if(curr.left == null) { //going left if the left child is empty

                    curr.left = new ArtifactNode(artifact);
                    artifactCount++;
                    return true;
                }

                curr = curr.left; //going further left if the left child is not empty
            }

            else if (artifact.getArtifactId() > curr.data.getArtifactId()) {
                
                if(curr.right == null) { //going right if the right child is empty
                    
                    curr.right = new ArtifactNode(artifact);
                    artifactCount++;
                    return true;
                }

                curr = curr.right; //going further right if the right child is not empty
            }

            else { //if the artifact ID already exists, we don't insert it

                System.out.println("Artifact ID already exists.");
                return false;
            }
        }
    }

    public Artifact search(int artifactId) {

        ArtifactNode curr = root;

        while(curr != null) {

            if(artifactId == curr.data.getArtifactId()) {

                return curr.data;
            }

            else if (artifactId < curr.data.getArtifactId()) {

                curr = curr.left;
            }

            else{

                curr = curr.right;
            }
        }

        return null; //returning null if the artifact is not found
    }

    public void inorder() {

        if (root == null) {

            System.out.println("Artifact database is empty.");
            return;
        }

        inorderRec(root);
    }

    private void inorderRec(ArtifactNode curr) {

        if (curr != null) { //prints left->root->right

            inorderRec(curr.left);
            System.out.println(curr.data);
            inorderRec(curr.right);
        }
    }

    public int size() {

        return artifactCount;
    }
}
