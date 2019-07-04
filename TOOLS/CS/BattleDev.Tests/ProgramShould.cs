// //  -----------------------------------------------------------------------
// //  <copyright file="ProgramShould.cs" company="AXA France Service">
// //      Copyright (c) AXA France Service. All rights reserved.
// //  </copyright>
// //  <actor>S821036 (VIGNERON Jean-baptiste)</actor>
// //  <created>28/03/2017 13:14</created>
// //  <modified>28/03/2017 14:33</modified>
// //  -----------------------------------------------------------------------

namespace BattleDev.Tests
{
    using System;
    using System.Globalization;
    using System.IO;
    using System.Reflection;
    using System.Text;

    using Microsoft.VisualStudio.TestTools.UnitTesting;

    using Shouldly;

    [TestClass]
    public class ProgramShould
    {
        private const int InputIndex = 1;

        private static readonly string InputOutputFolder = Path.Combine(Directory.GetCurrentDirectory(), "IO");

        [ClassInitialize]
        public static void ClassInitialize(TestContext context)
        {
            CultureInfo.DefaultThreadCurrentCulture = CultureInfo.InvariantCulture;
        }

        [TestMethod]
        public void PassAllTests()
        {
            for (var i = 1; i < 6; i++)
            {
                RunTest(i);
            }
        }

        [TestMethod]
        public void PassASingleTestFollowTheInputIndex()
        {
            RunTest(InputIndex);
        }

        private static void RunTest(int i)
        {
            string inputPath = $"{InputOutputFolder}\\input{i}.txt";
            
            if (!File.Exists(inputPath))
            {
                return;
            }

            var inputStream = File.OpenText(inputPath);
            Console.SetIn(inputStream);

            var consoleOutputBuilder = new StringBuilder();
            var consoleOutputWriter = new StringWriter(consoleOutputBuilder);
            Console.SetOut(consoleOutputWriter);

            Program.Main(new[] { string.Empty });

            string outputPath = $"{InputOutputFolder}\\output{i}.txt";

            if (!File.Exists(outputPath))
            {
                Assert.Fail("Fichier {0} introuvable", outputPath);
            }

            var outputStream = File.OpenText(outputPath);
            var expectedText = outputStream.ReadToEnd().Replace("\n\r", "\n");

            var actualText = consoleOutputBuilder.ToString().Trim().Replace("\r\n","\n");
            actualText.ShouldBe(expectedText, false, "Test n°" + i);
        }
    }
}