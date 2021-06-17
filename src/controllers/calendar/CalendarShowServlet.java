package controllers.calendar;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Custmer;
import models.WeekInfo;

/**
 * カレンダーを表示するクラス.
 */
@WebServlet("/calendar/show")
public class CalendarShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int SUN_NUM = 0;

    private static final int MON_NUM = 1;

    private static final int TUE_NUM = 2;

    private static final int WED_NUM = 3;

    private static final int THU_NUM = 4;

    private static final int FRI_NUM = 5;

    private static final int SAT_NUM = 6;

    /**
     * コンストラクタ.
     * @see HttpServlet#HttpServlet()
     */
    public CalendarShowServlet() {
        super();
    }

    /**
     * カレンダーを表示をするメソッド.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int year = 2021; // 2021年
        int month = 6;   // 6月


        List<Custmer> CustomerList = getCustomer(year, month);

        Map<Integer, ArrayList<Custmer>> customerMap = new HashMap<Integer, ArrayList<Custmer>>();

        for (int i = 0; i < CustomerList.size(); ++i) {

            Custmer customer = CustomerList.get(i);

            // Timestamp型からDate型へ
            Timestamp reserveTimestamp = customer.getReserve_day();

            Date date = new Date(reserveTimestamp.getTime());

            // Date型からCalendar型へ
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 予約日付を取得
            int reserveDay = cal.get(Calendar.DATE);

            ArrayList<Custmer> customers;

            // customerMapにキーが登録されいるか判定
            if (customerMap.containsKey(reserveDay)) {
                // 既にキーが登録されている場合
                customers = customerMap.get(reserveDay);
            } else {
                // キーが登録されていない場合
                customers = new ArrayList<Custmer>();
            }

            customers.add(customer);
            customerMap.put(reserveDay, customers);

        }

        // 週ごとのカレンダー情報を取得
        List<WeekInfo> weekList = getCalendarByWeeks(year, month, customerMap);

        request.setAttribute("year", year);         // 年
        request.setAttribute("month", month);       // 月
        request.setAttribute("weekList", weekList); // 週の情報

        // jspに遷移
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/calendar/show.jsp");
        rd.forward(request, response);
    }

    private List<Custmer> getCustomer(int year, int month) {

        List<Custmer> customerList = new ArrayList<Custmer>();
        Random rand = new Random();
        for (int i = 0; i < 25; i += 2) {
            Custmer custmer = new Custmer();
            //Calendarインスタンスの作成
            Calendar cal = Calendar.getInstance();

            //日付の設定
            cal.set (year, month - 1, i);
            custmer.setId(rand.nextInt(28));
            custmer.setReserve_day(new Timestamp(cal.getTimeInMillis()));
            customerList.add(custmer);
        }
        return customerList;
    }

    /**
     * 週ごとのカレンダー情報を取得.
     * @param year  取得対象の年
     * @param month 取得対象の月
     * @param customerMap カスタマー情報
     * @return 週ごとのカレンダー情報
     */
    private List<WeekInfo> getCalendarByWeeks(int year, int month, Map<Integer,
            ArrayList<Custmer>> customerMap) {
        //Calendarインスタンスの作成
        Calendar cal = Calendar.getInstance();

        //日付の設定
        cal.set (year, month - 1, 1);

        //曜日の取得
        int iweek = cal.get(Calendar.DAY_OF_WEEK);

        //最終日を取得
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        int maxdate = cal.get(Calendar.DATE);

        //日付部分
        int day=0;

        List<WeekInfo> weekList = new ArrayList<WeekInfo>();

        for (int rowweek = 0; rowweek < 6; rowweek++) {
          WeekInfo weekInfo = new WeekInfo();
          // 日曜日
          if (SUN_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setSunDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setSunDate(day);

              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setSunDateReceive(customerMap.get(day));
              }
          }

          // 月曜日
          if (MON_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setMonDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setMonDate(day);

              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setMonDateReceive(customerMap.get(day));
              }
          }

          // 火曜日
          if (TUE_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setTueDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setTueDate(day);
              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setTueDateReceive(customerMap.get(day));
              }

          }

          // 水曜日
          if (WED_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setWebDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setWebDate(day);

              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setWebDateReceive(customerMap.get(day));
              }
          }

          // 木曜日
          if (THU_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setThuDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setThuDate(day);

              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setThuDateReceive(customerMap.get(day));
              }
          }

          // 金曜日
          if (FRI_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setFriDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setFriDate(day);

              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setFriDateReceive(customerMap.get(day));
              }
          }

          // 土曜日
          if (SAT_NUM < iweek-1 && rowweek == 0) {
              weekInfo.setSatDate(null);
          } else if (day < maxdate) {
              day++;
              weekInfo.setSatDate(day);

              // 日付に紐つくデータがあるか判定
              if (customerMap.containsKey(day)) {
                  // データがある場合
                  weekInfo.setSatDateReceive(customerMap.get(day));
              }

          }
          weekList.add(weekInfo);
        }
        return weekList;
    }

}