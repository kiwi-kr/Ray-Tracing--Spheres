package assignment255;

public class Camera {

	Vector vuv;
	Vector vrp;
	Vector vrv;
	Vector vpn;
	Vector lookAt;
	
	public Camera (Vector vReferencePoint) { 
		this.vuv = new Vector(0, 1, 0);
		this.lookAt = new Vector(0, 0, 0);
		this.vrp = vReferencePoint;
		this.vpn = lookAt.sub(vReferencePoint);
		this.vpn.normalise();
		this.vrv = vpn.cross(vuv);
		this.vrv.normalise();
		this.vuv.cross(vpn);
		this.vuv.normalise();
	}
	
	public Vector makeRay(double x, double y) { 
		return vrp.add(vrv.mult(x)).add(vrv.mult(y));
	}
	
	public Vector calVPN(Vector lookAt, Vector vrp){
        return lookAt.sub(vrp);
    }


    public Vector calVRV(Vector vpn, Vector vuv){
        return vpn.cross(vuv);
    }

    public Vector calVUV (Vector vrv, Vector vpn){
        return vrv.cross(vpn);
    }
    public Vector calVRP(Vector vpn, Vector lookAt){
        return vpn.add(lookAt);
    }

    public Vector getVuv() {
        return vuv;
    }

    public void setVuv(Vector vuv) {
        this.vuv = vuv;
    }

    public Vector getVrp() {
        return vrp;
    }

    public void setVrp(Vector vrp) {
        this.vrp = vrp;
    }

    public Vector getVrv() {
        return vrv;
    }

    public void setVrv(Vector vrv) {
        this.vrv = vrv;
    }

    public Vector getVpn() {
        return vpn;
    }

    public void setVpn(Vector vpn) {
        this.vpn = vpn;
    }

    public Vector getLookAt() {
        return lookAt;
    }

    public void setLookAt(Vector lookAt) {
        this.lookAt = lookAt;
    }
	
	
	
}
