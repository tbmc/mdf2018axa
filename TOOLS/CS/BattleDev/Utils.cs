// //  -----------------------------------------------------------------------
// //  <copyright file="Utils.cs" company="AXA France Service">
// //      Copyright (c) AXA France Service. All rights reserved.
// //  </copyright>
// //  <actor>S821036 (VIGNERON Jean-baptiste)</actor>
// //  <created>28/03/2017 13:20</created>
// //  <modified>28/03/2017 14:25</modified>
// //  -----------------------------------------------------------------------

namespace BattleDev
{
    using System.Collections;
    using System.Collections.Generic;

    internal static class Utils
    {
        static Utils()
        {
            Messages = new List<string>();
        }

        public static IList<string> Messages { get; set; }

        public static void LocalPrint(string format, params string[] args)
        {
            Messages.Add(string.Format(format, args));
        }

        public static void LocalPrintArray(ICollection collection)
        {
            Messages.Add(collection.ToString());
        }

        public static void Clear()
        {
            Messages.Clear();
        }
    }
}