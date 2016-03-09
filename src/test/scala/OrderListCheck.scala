/**
  * Created by peter on 09/03/16.
  */

import org.scalacheck.{Prop, Gen, Properties}

object OrderList {
  def order(list: List[Int]) = {
    list.sorted
//    val newList = list match {
//      case Nil => Nil
//      case x :: Nil => list
//      case x :: y:: xs => list.::(x)
//    }
//
//    newList.sorted
//    List()
  }
}

object OrderListCheck extends Properties("Ordering a list") {

  def isOrdered(list: List[Int]): Boolean = {
    list match {
      case Nil => true
      case x :: Nil => true
      case x :: y :: xs => x <= y && isOrdered(xs)
    }
  }

  property("list is ordered") = Prop.forAll { list: List[Int] =>

    val orderedList: List[Int] = OrderList.order(list)

    isOrdered(orderedList)

  }

//  property("ordering and ordered list makes no difference") = Prop.forAll { list: List[Int] =>
////    This checks that the same elements exist in the ordered list
////    but will let though a list ordering that always returns an empty list
////    Stopped by length check but can still be beaten with ordering that changes duplications
//    val orderedList: List[Int] = OrderList.order(list)
//    OrderList.order(orderedList) == orderedList && orderedList.length == list.length
//  }

  property("ordering list does not add elements") = Prop.forAll { list: List[Int] =>
    val orderedList: List[Int] = OrderList.order(list)
    orderedList.diff(list) == List()
  }

  property("ordering list does not remove elements") = Prop.forAll { list: List[Int] =>
    val orderedList: List[Int] = OrderList.order(list)
    list.diff(orderedList) == List()
  }
}
