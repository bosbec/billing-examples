namespace Bosbec.CreateSpecificationExample.Helpers
{
    using System;
    using System.Configuration;

    public static class ConfigurationHelper
    {
        private static readonly Lazy<string> UsernameValue = new Lazy<string>(() =>
        {
            var username = ConfigurationManager.AppSettings["MobileResponseUsername"];

            if (string.IsNullOrEmpty(username))
            {
                throw new InvalidOperationException("Missing MobileResponse username in configuration file");
            }

            return username;
        });

        private static readonly Lazy<string> PasswordValue = new Lazy<string>(() =>
        {
            var password = ConfigurationManager.AppSettings["MobileResponsePassword"];

            if (string.IsNullOrEmpty(password))
            {
                throw new InvalidOperationException("Missing MobileResponse password in configuration file");
            }

            return password;
        });

        private static readonly Lazy<string> ApiBaseUrlValue = new Lazy<string>(() =>
        {
            var baseUrl = ConfigurationManager.AppSettings["MobileResponseApiBaseUrl"];

            if (string.IsNullOrEmpty(baseUrl))
            {
                throw new InvalidOperationException("Missing MobileResponse API base url in configuration file");
            }

            return baseUrl;
        });

        public static string Username
        {
            get { return UsernameValue.Value; }
        }

        public static string Password
        {
            get { return PasswordValue.Value; }
        }

        public static string ApiBaseUrl
        {
            get { return ApiBaseUrlValue.Value; }
        }
    }
}