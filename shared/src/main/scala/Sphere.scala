import Vec3.point3

class Sphere (val center: point3, val radius: Double, val material: Material) extends Hittable {

  override def hit(r: Ray, t_min: Double, t_max: Double, rec: HitRecord): Boolean = {
    val oc = r.origin - center
    val a = r.direction.lengthSquared()
    val half_b = Vec3.dot(oc, r.direction)
    val c = oc.lengthSquared() - radius * radius
    val delta = half_b * half_b - a * c
    if (delta > 0) {
      val root = Math.sqrt(delta)
      var temp = (-half_b - root) / a
      if (temp < t_max && temp > t_min) {
        rec.t = temp
        rec.p = r.at(rec.t)
        rec.normal = (rec.p - center) /= radius
        val outward_normal = (rec.p - center) /= radius
        rec.setFaceNormal(r, outward_normal)
        rec.material = material
        return true
      }
      else{
        temp = (-half_b + root) / a
        if (temp < t_max && temp > t_min) {
          rec.t = temp
          rec.p = r.at(rec.t)
          rec.normal = (rec.p - center) /= radius
          val outward_normal = (rec.p - center) /= radius
          rec.setFaceNormal(r, outward_normal)
          rec.material = material
          return true
        }
      }
    }
    false
  }
}
